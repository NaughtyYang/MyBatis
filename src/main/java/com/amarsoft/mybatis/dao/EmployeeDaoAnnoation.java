package com.amarsoft.mybatis.dao;

import com.amarsoft.mybatis.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author yzhao
 * @date 2022-07-16
 */

public interface EmployeeDaoAnnoation {
    @Select("select * from employee where id = #{id}")
    Employee getEmpByID(Integer id);
}
