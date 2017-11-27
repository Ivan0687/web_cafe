package name.ivan.boiko.dao.jdbcDao;

import name.ivan.boiko.dao.GenericDao;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractGenericJdbcDao<K extends Serializable, V extends Serializable> implements GenericDao<K, V> {

    private final DataSource dataSource;

    public AbstractGenericJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    protected Connection getConnection() throws SQLException {

        return getDataSource().getConnection();
    }
}
