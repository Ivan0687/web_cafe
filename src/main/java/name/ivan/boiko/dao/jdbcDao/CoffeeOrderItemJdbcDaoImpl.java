package name.ivan.boiko.dao.jdbcDao;

import name.ivan.boiko.dao.CoffeeOrderDao;
import name.ivan.boiko.dao.CoffeeOrderItemDao;
import name.ivan.boiko.dao.CoffeeTypeDao;
import name.ivan.boiko.model.CoffeeOrder;
import name.ivan.boiko.model.CoffeeOrderItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoffeeOrderItemJdbcDaoImpl extends AbstractGenericJdbcDao<Integer, CoffeeOrderItem> implements CoffeeOrderItemDao {

    private final CoffeeOrderDao orderDao = new CoffeeOrderJdbcDaoImpl(getDataSource());
    private final CoffeeTypeDao typeDao = new CoffeeTypeJdbcDaoImpl(getDataSource());

    public CoffeeOrderItemJdbcDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(CoffeeOrderItem item) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO coffeeorderitem (order_id, type_id, quantity) VALUES (?, ?, ?)")) {

            statement.setInt(1, item.getCoffeeOrder().getId());
            statement.setInt(2, item.getCoffeeType().getId());
            statement.setInt(3, item.getQuantity());

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll(List<CoffeeOrderItem> items) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO coffeeorderitem (order_id, type_id, quantity) VALUES (?, ?, ?)")) {

            for (CoffeeOrderItem item : items) {
                statement.setInt(1, item.getCoffeeOrder().getId());
                statement.setInt(2, item.getCoffeeType().getId());
                statement.setInt(3, item.getQuantity());
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CoffeeOrderItem read(Integer id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM coffeeorderitem WHERE id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                CoffeeOrderItem item = new CoffeeOrderItem();

                if (resultSet.next()) {

                    item.setId(resultSet.getInt("id"));
                    item.setCoffeeOrder(orderDao.read(resultSet.getInt("order_id")));
                    item.setCoffeeType(typeDao.read(resultSet.getInt("type_id")));
                    item.setQuantity(resultSet.getInt("quantity"));

                    return item;

                } else return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CoffeeOrderItem> readAll() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM coffeeorderitem")) {

            try (ResultSet resultSet = statement.executeQuery()) {

                ArrayList<CoffeeOrderItem> list = new ArrayList<>();

                while (resultSet.next()) {

                    CoffeeOrderItem item = new CoffeeOrderItem();

                    item.setId(resultSet.getInt("id"));
                    item.setCoffeeOrder(orderDao.read(resultSet.getInt("order_id")));
                    item.setCoffeeType(typeDao.read(resultSet.getInt("type_id")));
                    item.setQuantity(resultSet.getInt("quantity"));

                    list.add(item);
                }
                return list;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CoffeeOrderItem> readAllByOrderId(Integer orderId) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM coffeeorderitem WHERE order_id LIKE ?")) {

            statement.setInt(1, orderId);


            try (ResultSet resultSet = statement.executeQuery()) {

                ArrayList<CoffeeOrderItem> list = new ArrayList<>();

                if (resultSet.next()) {

                    CoffeeOrder order = orderDao.read(resultSet.getInt("order_id"));

                    CoffeeOrderItem item = new CoffeeOrderItem();

                    item.setId(resultSet.getInt("id"));
                    item.setCoffeeOrder(order);
                    item.setCoffeeType(typeDao.read(resultSet.getInt("type_id")));
                    item.setQuantity(resultSet.getInt("quantity"));

                    list.add(item);

                    while (resultSet.next()) {

                        CoffeeOrderItem coffeeOrderItem = new CoffeeOrderItem();

                        coffeeOrderItem.setId(resultSet.getInt("id"));
                        coffeeOrderItem.setCoffeeOrder(order);
                        coffeeOrderItem.setCoffeeType(typeDao.read(resultSet.getInt("type_id")));
                        coffeeOrderItem.setQuantity(resultSet.getInt("quantity"));

                        list.add(item);
                    }
                }
                return list;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CoffeeOrderItem item) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE coffeeorderitem SET order_id = ?, type_id = ?, quantity = ? WHERE id = ?")) {

            statement.setInt(1, item.getCoffeeOrder().getId());
            statement.setInt(2, item.getCoffeeType().getId());
            statement.setInt(3, item.getQuantity());
            statement.setInt(4, item.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM coffeeorderitem WHERE id = ?")) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
