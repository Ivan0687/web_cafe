package name.ivan.boiko.dao.jdbcDao;

import name.ivan.boiko.dao.GenericDao;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractGenericJdbcDao<K extends Serializable, V extends Serializable> implements GenericDao<K, V> {

    private final String URL = getProperties().getProperty("db.url");
    private final String USER = getProperties().getProperty("db.username");
    private final String PASS = getProperties().getProperty("db.password");

    private Properties getProperties(){

        Properties properties = new Properties();

        try(InputStream input = new FileInputStream("db.properties")){

            properties.load(input);

        } catch (IOException e) {
           throw new RuntimeException(e);
        }

        return properties;
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
