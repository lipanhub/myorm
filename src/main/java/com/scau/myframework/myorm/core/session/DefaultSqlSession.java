package com.scau.myframework.myorm.core.session;

import com.scau.myframework.myorm.core.binding.MapperProxy;
import com.scau.myframework.myorm.entity.Configuration;
import com.scau.myframework.myorm.entity.MappedStatement;
import com.scau.myframework.myorm.core.excutor.Executor;
import com.scau.myframework.myorm.core.excutor.DefaultExecutor;

import java.lang.reflect.Proxy;
import java.util.List;


/**
 * @description:
 * @author: lipan
 * @time: 2020/1/13 21:21
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        executor = new DefaultExecutor(configuration);
    }

    @Override
    public <T> T selectOne(String statement, Object[] parameter) {

        List<Object> selectList = this.selectList(statement,parameter);
        if(null == selectList || selectList.size() == 0) {
            return null;
        }
        if(selectList.size() == 1){
            return (T) selectList.get(0);
        }else{
            throw new RuntimeException("Too Many Results!");
        }

    }

    @Override
    public <E> List<E> selectList(String statement, Object[] parameter) {

        MappedStatement mappedStatement = configuration.getMappedStatements().get(statement);
        return executor.query(mappedStatement, parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        MapperProxy mapperProxy = new MapperProxy(this);
        return (T) Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},mapperProxy);
    }
}
