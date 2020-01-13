package com.scau.myframework.test;

import com.scau.myframework.myorm.session.SqlSession;
import com.scau.myframework.myorm.session.SqlSessionFactory;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.InputStream;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/13 21:42
 */
public class ConfigurationTest {

    @Test
    public void configurationTest(){

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        System.out.println(session);
    }
}
