package com.scau.myframework.myorm.core;

import com.scau.myframework.myorm.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 根据配置信息，维持连接对象的管理(增加连接池功能)
 *
 * @author lipan
 */
public class DBManager {

    private static ConnectionPool connectionPool = new ConnectionPool();

    public static Connection getConnection() {
        return connectionPool.getConnection();
    }

    public static void close(ResultSet rs, Statement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(ps,conn);
    }

    public static void close(Statement ps, Connection conn) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn);
    }

    public static void close(Connection conn) {
        connectionPool.close(conn);
    }
}
