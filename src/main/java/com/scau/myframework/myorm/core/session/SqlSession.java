package com.scau.myframework.myorm.core.session;

import java.util.List;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/13 20:44
 */
public interface SqlSession {
     <T> T selectOne(String statement,Object[] parameter);
     <E> List<E> selectList(String statement, Object[] parameter);
     <T> T getMapper(Class<T> type);
}
