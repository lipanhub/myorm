package com.scau.myframework.myorm.core.excutor;

import com.scau.myframework.myorm.entity.MappedStatement;

import java.util.List;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/13 23:37
 */
public interface Executor {

    /**
     *
     * @param mappedStatement
     * @param parameter
     * @param <E>
     * @return
     */
    <E> List<E> query(MappedStatement mappedStatement,Object[] parameter);
}
