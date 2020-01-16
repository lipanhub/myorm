package com.scau.myframework.test.dao;

import com.scau.myframework.test.entity.Employee;

import java.util.List;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/15 20:45
 */
public interface EmployeeMapper {
    Employee selectByPrimaryKey(Integer id);
    List<Employee> selectAll();
}
