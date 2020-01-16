package com.scau.myframework.myorm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/15 21:49
 */
public class ReflectUtils {
    public static void setPropToBeanFromResultSet(Object entity, ResultSet resultSet) throws SQLException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            if (declaredFields[i].getType().getSimpleName().equals("String")) {
                setPropToBean(entity, declaredFields[i].getName(), resultSet.getString(declaredFields[i].getName()));
            } else if (declaredFields[i].getType().getSimpleName().equals("Integer")) {
                setPropToBean(entity, declaredFields[i].getName(), resultSet.getString(declaredFields[i].getName()));
            } else if (declaredFields[i].getType().getSimpleName().equals("Long")) {
                setPropToBean(entity, declaredFields[i].getName(), resultSet.getString(declaredFields[i].getName()));
            }
        }
    }

    private static void setPropToBean(Object bean, String propName, Object value) {
        Field field;
        try {
            field = bean.getClass().getDeclaredField(propName);
            field.setAccessible(true);
            field.set(bean,value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用obj对象对应属性fieldName的get方法
     *
     * @param fieldName
     * @param obj
     * @return
     */
    public static Object invokeGet(String fieldName, Object obj) {
        try {
            Class c = obj.getClass();
            Method m = c.getDeclaredMethod("get" + StringUtils.firstChar2UpperCase(fieldName), null);
            return m.invoke(obj, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void invokeSet(Object obj, String columnName, Object columnValue) {
        try {
            if (null != columnValue) {
                Method m = obj.getClass().getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName),
                        columnValue.getClass());
                m.invoke(obj, columnValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
