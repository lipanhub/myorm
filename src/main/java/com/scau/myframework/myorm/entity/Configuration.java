package com.scau.myframework.myorm.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/13 19:18
 */
public class Configuration {
    /**
     * 驱动类
     */
    private String driver;
    /**
     * jdbc的url
     */
    private String url;
    /**
     * 数据库的用户名
     */
    private String user;
    /**
     * 数据库的密码
     */
    private String password;
    /**
     * 存储所有的mapper.xml对象
     */
    private Map<String,MappedStatement> mappedStatements = new HashMap<String,MappedStatement>();

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }
}
