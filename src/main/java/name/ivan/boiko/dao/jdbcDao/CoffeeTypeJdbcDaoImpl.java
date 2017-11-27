package name.ivan.boiko.dao.jdbcDao;

import name.ivan.boiko.dao.CoffeeTypeDao;
import name.ivan.boiko.model.CoffeeType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoffeeTypeJdbcDaoImpl extends AbstractGenericJdbcDao<Integer, CoffeeType> implements CoffeeTypeDao {

    public CoffeeTypeJdbcDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(CoffeeType coffeeType) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO coffeeType (name, price) VALUES (?, ?)")) {

            statement.setString(1, coffeeType.getName());
            statement.setBigDecimal(2, coffeeType.getPrice());

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CoffeeType read(Integer id) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM coffeeType WHERE id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                CoffeeType coffeeType = new CoffeeType();

                if (resultSet.next()) {
                    coffeeType.setId(resultSet.getInt("id"));
                    coffeeType.setName(resultSet.getString("name"));
                    coffeeType.setPrice(resultSet.getBigDecimal("price"));
                    if (resultSet.getString("disabled").equals("Y")) {
                        coffeeType.setDisabled(true);
                    }
                    return coffeeType;

                } else return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CoffeeType> readAll() {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM coffeeType")) {

            try (ResultSet resultSet = statement.executeQuery()) {

                ArrayList<CoffeeType> list = new ArrayList<>();

                while (resultSet.next()) {
                    CoffeeType coffeeType = new CoffeeType();
                    coffeeType.setId(resultSet.getInt("id"));
                    coffeeType.setName(resultSet.getString("name"));
                    coffeeType.setPrice(resultSet.getBigDecimal("price"));
                    if (resultSet.getString("disabled").equals("Y")) {
                        coffeeType.setDisabled(true);
                    }
                    list.add(coffeeType);
                }
                return list;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CoffeeType> readAllEnabled() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM coffeeType WHERE disabled NOT LIKE 'Y'")) {

            try (ResultSet resultSet = statement.executeQuery()) {

                ArrayList<CoffeeType> list = new ArrayList<>();

                while (resultSet.next()) {
                    CoffeeType coffeeType = new CoffeeType();
                    coffeeType.setId(resultSet.getInt("id"));
                    coffeeType.setName(resultSet.getString("name"));
                    coffeeType.setPrice(resultSet.getBigDecimal("price"));

                    list.add(coffeeType);
                }
                return list;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CoffeeType coffeeType) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE coffeeType SET name = ?, price = ?, disabled = ? WHERE id = ?")) {

            statement.setString(1, coffeeType.getName());
            statement.setBigDecimal(2, coffeeType.getPrice());

            if (coffeeType.isDisabled()) {
                statement.setString(3, "Y");
            } else {
                statement.setString(3, "N");
            }
            statement.setInt(4, coffeeType.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM coffeeType WHERE id = ?")) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
