package com.amarsoft.mybatis.test;

import com.amarsoft.mybatis.dao.DepartmentMapper;
import com.amarsoft.mybatis.dao.EmployeeDynamicMapper;
import com.amarsoft.mybatis.dao.EmployeeMapper;
import com.amarsoft.mybatis.entity.Department;
import com.amarsoft.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yzhao
 * @date 2022-07-15
 */
public class MybatisTest {
    public SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //可以直接执行已经映射的sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
    @Test
    public void test() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try {
            Employee employee = sqlSession.selectOne("com.amarsoft.mybatis.entity.Employee.selectEmployee", 1);
            System.out.println(employee.getEmail());
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void test2() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//            System.out.println(mapper.getEmpByID(1).toString());
            //System.out.println(mapper.getEmpByIDAndName("zhaoyang",13).toString());
//            Map<String,Object> map = new HashMap<>();
//            map.put("id",1);
//            System.out.println(mapper.getEmpByMap(map));

//            List<Employee> zha = mapper.getEmpForList("%zha%");

//            Employee empAndDept = mapper.getEmpAndDept(1);
//            System.out.println(empAndDept.toString());

            Employee emp = mapper.getEmpByIDStep(1);
            System.out.println(emp.toString());

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test3() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.insertEmp(new Employee(null,"zhaoyang","1","2212335948@qq.com"));
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test4() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            for (Employee employee : mapper.getEmpsByID(1)) {
                System.out.println(employee.getLastName());
            }

            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test5() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try {
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            Employee employee = new Employee();
            employee.setId(1);
            List<Employee> empsByIF = mapper.getEmpsByIF(employee);
            for (Employee e:empsByIF
                 ) {
                System.out.println(e.getLastName());
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test6() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try {
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            Employee employee = new Employee();
            employee.setId(1);
            employee.setLastName("DaMing");
            mapper.updateEmp(employee);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test7() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try {
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            List<Employee> empsByIFForEach = mapper.getEmpsByIFForEach(Arrays.asList(1, 2));
            System.out.println(empsByIFForEach);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test8() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try {
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            Employee employee = new Employee(null,"Simith","0","895554778@qq.com",new Department(1));
            Employee employee2 = new Employee(null,"Emma","0","8g33554778@qq.com",new Department(1));
            List<Employee> list = new ArrayList<>();
            list.add(employee);
            list.add(employee2);
            mapper.addEmps(list);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }
}
