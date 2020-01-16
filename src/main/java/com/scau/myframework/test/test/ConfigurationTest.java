package com.scau.myframework.test.test;

import com.scau.myframework.myorm.core.session.DefaultSqlSessionFactory;
import com.scau.myframework.myorm.core.session.SqlSession;
import com.scau.myframework.test.dao.EmployeeMapper;
import com.scau.myframework.test.entity.Employee;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.List;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/13 21:42
 */
public class ConfigurationTest {

    @Test
    public void configurationTest() {

        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory();
        SqlSession session = defaultSqlSessionFactory.openSession();
        System.out.println(session);
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        if (null == employeeMapper){
            System.out.println("sdfsdf");
        }
        List<Employee> employees = employeeMapper.selectAll();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        //Employee employee = employeeMapper.selectByPrimaryKey(4);
        //System.out.println(employee);
    }
}
