package com.amarsoft.mybatis.dao;

import com.amarsoft.mybatis.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yzhao
 * @date 2022-07-17
 */
public interface EmployeeDynamicMapper {
    List<Employee> getEmpsByIF(Employee employee);
    List<Employee> getEmpsByIFTrim(Employee employee);
    List<Employee> getEmpsByIFChoose(Employee employee);
    void updateEmp(Employee employee);
    List<Employee> getEmpsByIFForEach(@Param("ids") List<Integer> ids);
    void addEmps(List<Employee> list);
}
