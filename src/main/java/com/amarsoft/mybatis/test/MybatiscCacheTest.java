package com.amarsoft.mybatis.test;

import com.amarsoft.mybatis.dao.DepartmentMapper;
import com.amarsoft.mybatis.dao.EmployeeDynamicMapper;
import com.amarsoft.mybatis.dao.EmployeeMapper;
import com.amarsoft.mybatis.entity.Department;
import com.amarsoft.mybatis.entity.Employee;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author yzhao
 * @date 2022-07-15
 */
public class MybatiscCacheTest {
    public SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //可以直接执行已经映射的sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    /**
     * 一级缓存是sqlsession级别的，一直开启的，没法关闭，只要缓存中有了，就不用再去DB查了
     *  一级缓存失效的情况：
     *      1.sqlsession不同
     *      2.查询sql不同
     *      3.同一个sqlsession，但是两次查询之间进行了增删改操作
     *      4.同一个sqlsession，手动清楚缓存  sqlSession.clearCache();
     *
     *  二级缓存是namespace级别的，默认是开启的  <setting name="cacheEnabled " value="true"/>
     *  二级缓存需要去每个mapper.xml中使用  <cache></cache>
     *  二级缓存的意义在于：同一个namespace的不同sqlsession，如果第一个sqlsession关闭，则会将一级缓存放进二级缓存中，其他sqlsession就可以使用了
     */
    @Test
    public void test() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            //分页
            Page<Object> page = PageHelper.startPage(1, 5);
            System.out.println("当前页码:"+page.getPageNum());

            //分页2
            List<Employee> yy = mapper.getEmpForList("yy");
            PageInfo<Employee> pageInfo = new PageInfo<>(yy);
            pageInfo.getPageNum();

            Employee emp = mapper.getEmpByID(1);
            System.out.println(emp.getLastName());

            Employee emp2 = mapper.getEmpByID(1);
            System.out.println(emp.getLastName());

            //true
            System.out.println(emp == emp2);

            //手动清除一级缓存
            sqlSession.clearCache();

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testBatch() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //调用批量sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        for (int i =0;i<=10000;i++){
            mapper.insertEmp(new Employee(Integer.valueOf(UUID.randomUUID().toString()),"","",""));
        }

        sqlSession.commit();
    }
}
