package com.scau.myframework.myorm.core.excutor;

import com.scau.myframework.myorm.core.DBManager;
import com.scau.myframework.myorm.entity.Configuration;
import com.scau.myframework.myorm.entity.MappedStatement;
import com.scau.myframework.myorm.util.JDBCUtils;
import com.scau.myframework.myorm.util.ReflectUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/13 23:37
 */
public class DefaultExecutor implements Executor {

    private final Configuration configuration;

    public DefaultExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> query(MappedStatement mappedStatement, Object[] parameter) {
        List<E> ret = new ArrayList<E>();

        Connection connection = DBManager.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = connection.prepareStatement(mappedStatement.getSql());
            JDBCUtils.handleParams(ps, parameter);
            String logMsg = ps.toString().substring(ps.toString().indexOf(":") + 1, ps.toString().length());
            System.out.println("orm log----->" + logMsg);
            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                if (ret == null) {
                    ret = new ArrayList();
                }
                Class<?> clazz = Class.forName(mappedStatement.getResultType());
                E rowObj = (E)clazz.newInstance();

                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);

                    //调用rowObj对象的setUsername(String uname)方法，将columnValue的值设置进去
                    ReflectUtils.invokeSet(rowObj, columnName, columnValue);
                }

                ret.add(rowObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
