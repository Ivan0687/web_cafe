package name.ivan.boiko.dao.jdbcDao;

import name.ivan.boiko.dao.GenericDao;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractGenericJdbcDao<K extends Serializable, V extends Serializable> implements GenericDao<K, V> {

    private final String URL = getProperties().getProperty("db,url");
    private final String USER = getProperties().getProperty("db.username");
    private final String PASS = getProperties().getProperty("db.password");

    private final Class<V> entityClass;

    AbstractGenericJdbcDao(Class<V> entityClass) {
        this.entityClass = entityClass;
    }

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

    String entityClassName(){
        return entityClass.getSimpleName();
    }

    public void delete(Integer id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ? WHERE id = ?")) {

            preparedStatement.setString(1, entityClassName());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
