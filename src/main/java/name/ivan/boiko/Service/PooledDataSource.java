package name.ivan.boiko.Service;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class PooledDataSource {

    private static String URL = getProperties().getProperty("db.url");
    private static String USER = getProperties().getProperty("db.username");
    private static String PASS = getProperties().getProperty("db.password");
    private static String DRIVER = getProperties().getProperty("db.driver");

    private static Properties getProperties() {

        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream input = loader.getResourceAsStream("db.properties")) {

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

    public static DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        dataSource.setDriverClassName(DRIVER);

        dataSource.setInitialSize(5);

        return dataSource;
    }
}
