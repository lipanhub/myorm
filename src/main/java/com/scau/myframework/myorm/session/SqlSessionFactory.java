package com.scau.myframework.myorm.session;

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
public class SqlSessionFactory {

    private final Configuration configuration = new Configuration();

    public SqlSessionFactory() {
        loadDbInfo();
        loadMappersInfo();
    }

    private void loadDbInfo() {
        InputStream dbIn = SqlSessionFactory.class.getClassLoader().getResourceAsStream("db.properties");
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
    }

    private void loadMappersInfo() {
        URL resources = null;
        resources = SqlSessionFactory.class.getClassLoader().getResource("mappers");
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

    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
