package name.ivan.boiko.dao.jdbcDao;

import name.ivan.boiko.dao.CoffeeTypeDao;
import name.ivan.boiko.model.CoffeeType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CoffeeTypeJdbcDaoImpl extends AbstractGenericJdbcDao<Integer, CoffeeType> implements CoffeeTypeDao {

    @Override
    public void create(CoffeeType coffeeType) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "insert into CoffeeType (name, price) values (?, ?)")) {

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
                     "select * from CoffeeType where id = ?")) {

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
    public Collection<CoffeeType> read() {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select * from CoffeeType")) {

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
    public void update(CoffeeType coffeeType) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "update CoffeeType set name = ?, price = ?, disabled = ? where id = ?")) {

            statement.setString(1, coffeeType.getName());
            statement.setBigDecimal(2, coffeeType.getPrice());

            if (coffeeType.isDisabled())
                statement.setString(3, "Y");
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
                     "delete from CoffeeType where id = ?")) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
