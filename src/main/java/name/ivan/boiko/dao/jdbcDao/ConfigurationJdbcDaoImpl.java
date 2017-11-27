package name.ivan.boiko.dao.jdbcDao;

import name.ivan.boiko.dao.ConfigurationDao;
import name.ivan.boiko.model.CoffeeConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationJdbcDaoImpl extends AbstractGenericJdbcDao<String, CoffeeConfiguration> implements ConfigurationDao {

    public ConfigurationJdbcDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(CoffeeConfiguration configuration) {
        try (Connection connection = getConnection()){
            save(connection, configuration);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Connection connection, CoffeeConfiguration configuration) {

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO configuration (id, value) VALUES (?, ?)")) {

            statement.setString(1, configuration.getId());
            statement.setInt(2, configuration.getValue());

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CoffeeConfiguration read(String id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM configuration WHERE id = ?")) {

            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    CoffeeConfiguration configuration = new CoffeeConfiguration();
                    configuration.setId(resultSet.getString("id"));
                    configuration.setValue(resultSet.getInt("value"));
                    return configuration;

                } else return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CoffeeConfiguration> readAll() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM Configuration")) {

            try (ResultSet resultSet = statement.executeQuery()) {

                ArrayList<CoffeeConfiguration> list = new ArrayList<>();

                while (resultSet.next()) {
                    CoffeeConfiguration configuration = new CoffeeConfiguration();
                    configuration.setId(resultSet.getString("id"));
                    configuration.setValue(resultSet.getInt("value"));

                    list.add(configuration);
                }
                return list;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CoffeeConfiguration configuration) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE configuration SET value = ? WHERE id = ?")) {

            statement.setInt(1, configuration.getValue());
            statement.setString(2, configuration.getId());


            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM configuration WHERE id = ?")) {

            statement.setString(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
