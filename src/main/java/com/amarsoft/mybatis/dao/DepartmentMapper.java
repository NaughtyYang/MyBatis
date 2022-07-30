package com.amarsoft.mybatis.dao;

import com.amarsoft.mybatis.entity.Department;
import com.amarsoft.mybatis.entity.Employee;

import java.util.List;

/**
 * @author yzhao
 * @date 2022-07-17
 */
public interface DepartmentMapper {
    public Department getDepartmentByID(Integer id);
    public List<Employee> getEmpsByID(Integer id);
}
