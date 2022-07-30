package com.amarsoft.mybatis.dao;

import com.amarsoft.mybatis.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author yzhao
 * @date 2022-07-15
 */
public interface EmployeeMapper {
    Employee getEmpByID(Integer id);

    Employee getEmpByIDStep(Integer id);

//    @Select(value = "select * from \"employee_yyy\" where \"last_name\" = #{lastName} and \"id\" = #{id}",databaseId = "oracle")
//    Employee getEmpByIDAndName(@Param("lastName") String lastName, @Param("id") Integer id);
    //增删改支持自定义返回值类型   integer,long,boolean
    void insertEmp(Employee employee);
    void updateEmpByID(Employee employee);
    void deleteEmpByID(Integer id);

    //多个参数  mybatis会将参数封装为一个map集合，key=param1,param2  ,所以取值的时候可以用：#{0}，#{1} ，也可以用：#{param1}
    Employee getEmpByMap(Map<String,Object> map);

    List<Employee> getEmpForList(String name);

    Employee getEmpAndDept(Integer id);
}
