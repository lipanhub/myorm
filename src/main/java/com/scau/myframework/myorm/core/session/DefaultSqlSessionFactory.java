package com.scau.myframework.myorm.core.session;

import com.scau.myframework.myorm.entity.Configuration;
import com.scau.myframework.myorm.entity.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * @description: 加载配置文件，生产SqlSession
 * @author: lipan
 * @time: 2020/1/13 20:44
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private final Configuration configuration = new Configuration();

    public DefaultSqlSessionFactory() {
        loadDbInfo();
        loadMappersInfo();
    }

    private void loadDbInfo() {
        InputStream dbIn = DefaultSqlSessionFactory.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(dbIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration.setDriver(properties.get("driver").toString());
        configuration.setUrl(properties.get("url").toString());
        configuration.setUser(properties.get("user").toString());
        configuration.setPassword(properties.get("password").toString());
        configuration.setUsingDB(properties.get("usingDB").toString());
        configuration.setSrcPath(properties.get("srcPath").toString());
        configuration.setPoPackage(properties.get("poPackage").toString());
        configuration.setPoolMinSize(Integer.parseInt(properties.get("poolMinSize").toString()));
        configuration.setPoolMaxSize(Integer.parseInt(properties.get("poolMaxSize").toString()));
    }

    private void loadMappersInfo() {
        URL resources = null;
        resources = DefaultSqlSessionFactory.class.getClassLoader().getResource("mappers");
        File mappers = new File(resources.getFile());
        if (mappers.isDirectory()) {
            File[] listFiles = mappers.listFiles();
            for (File file : listFiles) {
                loadMapperInfo(file);
            }
        }

    }

    private void loadMapperInfo(File file) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        String namespace = root.attribute("namespace").getData().toString();
        List<Element> selects = root.elements("select");
        for (Element element : selects) {
            MappedStatement mappedStatement = new MappedStatement();
            String id = element.attribute("id").getData().toString();
            String resultType = element.attribute("resultType").getData().toString();
            String sql = element.getData().toString();
            String sourceId = namespace + "." + id;
            mappedStatement.setNamespace(namespace);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSourceId(sourceId);
            mappedStatement.setSql(sql);
            configuration.getMappedStatements().put(sourceId, mappedStatement);
        }
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
