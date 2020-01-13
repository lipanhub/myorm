package com.scau.myframework.myorm.session;

import com.scau.myframework.myorm.entity.Configuration;

import java.util.List;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/13 21:21
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession() {
    }

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return null;
    }
    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return null;
    }
    @Override
    public <T> T getMapper(String statement, Object parameter) {
        return null;
    }
}
