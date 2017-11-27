package name.ivan.boiko.dao.jdbcDao;

import name.ivan.boiko.dao.CoffeeOrderDao;
import name.ivan.boiko.dao.CoffeeOrderItemDao;
import name.ivan.boiko.model.CoffeeOrder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoffeeOrderJdbcDaoImpl extends AbstractGenericJdbcDao<Integer, CoffeeOrder> implements CoffeeOrderDao {

//    private final CoffeeOrderItemDao orderItemDao = new CoffeeOrderItemJdbcDaoImpl(getDataSource());

    public CoffeeOrderJdbcDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(CoffeeOrder coffeeOrder) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO coffeeorder (name, delivery_address, cost) VALUES (?, ?, ?)")) {

            statement.setString(1, coffeeOrder.getName());
            statement.setString(2, coffeeOrder.getDeliveryAddress());
            statement.setBigDecimal(3, coffeeOrder.getCost());

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CoffeeOrder saveId(CoffeeOrder coffeeOrder) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO coffeeorder (name, delivery_address, cost) VALUES (?, ?, ?)", new String[] {"id"})) {

            statement.setString(1, coffeeOrder.getName());
            statement.setString(2, coffeeOrder.getDeliveryAddress());
            statement.setBigDecimal(3, coffeeOrder.getCost());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                coffeeOrder.setId(generatedKeys.getInt(1));
                coffeeOrder.setOrderDate(generatedKeys.getTimestamp(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coffeeOrder;
    }




    @Override
    public CoffeeOrder read(Integer id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM coffeeorder WHERE id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                CoffeeOrder coffeeOrder = new CoffeeOrder();

                if (resultSet.next()) {
                    coffeeOrder.setId(resultSet.getInt("id"));
                    coffeeOrder.setOrderDate(resultSet.getTimestamp("date"));
                    coffeeOrder.setName(resultSet.getString("name"));
                    coffeeOrder.setDeliveryAddress(resultSet.getString("delivery_address"));
                    coffeeOrder.setCost(resultSet.getBigDecimal("cost"));

//                    coffeeOrder.setItems(orderItemDao.readAllByOrderId(coffeeOrder.getId()));

                    return coffeeOrder;

                } else return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CoffeeOrder> readAll() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM coffeeorder")) {

            try (ResultSet resultSet = statement.executeQuery()) {

                ArrayList<CoffeeOrder> list = new ArrayList<>();

                while (resultSet.next()) {

                    CoffeeOrder coffeeOrder = new CoffeeOrder();

                    coffeeOrder.setId(resultSet.getInt("id"));
                    coffeeOrder.setOrderDate(resultSet.getTimestamp("date"));
                    coffeeOrder.setName(resultSet.getString("name"));
                    coffeeOrder.setDeliveryAddress(resultSet.getString("delivery_address"));
                    coffeeOrder.setCost(resultSet.getBigDecimal("cost"));

//                    coffeeOrder.setItems(orderItemDao.readAllByOrderId(coffeeOrder.getId()));

                    list.add(coffeeOrder);
                }
                return list;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CoffeeOrder coffeeOrder) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE coffeeorder SET name = ?, delivery_address = ?, cost = ? WHERE id = ?")) {

            statement.setString(1, coffeeOrder.getName());
            statement.setString(2, coffeeOrder.getDeliveryAddress());
            statement.setBigDecimal(3, coffeeOrder.getCost());
            statement.setInt(4, coffeeOrder.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM coffeeorder WHERE id = ?")) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
