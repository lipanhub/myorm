package com.scau.myframework.myorm.core.session;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/12 18:30
 */
public interface SqlSessionFactory {

	/**
	 *
	 * @return
	 */
	public SqlSession openSession();

}
