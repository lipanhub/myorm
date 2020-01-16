package com.scau.myframework.myorm.core.binding;

import com.scau.myframework.myorm.core.session.SqlSession;

import javax.sound.midi.Soundbank;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/14 0:03
 */
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String statement = method.getDeclaringClass().getName() + "." + method.getName();


        if (Collection.class.isAssignableFrom(method.getReturnType())) {
            return sqlSession.selectList(statement, args);
        } else {
            return sqlSession.selectOne(statement, args);
        }
    }
}
