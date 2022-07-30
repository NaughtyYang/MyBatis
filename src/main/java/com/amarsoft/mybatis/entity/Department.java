package com.amarsoft.mybatis.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * @author yzhao
 * @date 2022-07-16
 */
@Alias("dept")
public class Department implements Serializable {

    private static final long serializable_ID = 1L;
    private Integer id;
    private String name;
    private List<Employee> emps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    public Department(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
