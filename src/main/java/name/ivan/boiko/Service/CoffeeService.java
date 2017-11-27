package name.ivan.boiko.Service;

import name.ivan.boiko.dao.CoffeeOrderDao;
import name.ivan.boiko.dao.CoffeeOrderItemDao;
import name.ivan.boiko.dao.jdbcDao.CoffeeOrderItemJdbcDaoImpl;
import name.ivan.boiko.dao.jdbcDao.CoffeeOrderJdbcDaoImpl;
import name.ivan.boiko.model.CoffeeOrder;
import name.ivan.boiko.model.CoffeeOrderItem;

import javax.sql.DataSource;

public class CoffeeService {

    private DataSource dataSource = PooledDataSource.getDataSource();

    private CoffeeOrderDao orderDao = new CoffeeOrderJdbcDaoImpl(dataSource);

    private CoffeeOrderItemDao itemDao = new CoffeeOrderItemJdbcDaoImpl(dataSource);

    public CoffeeOrder saveOrder(CoffeeOrder coffeeOrder){

        CoffeeOrder order = orderDao.saveId(coffeeOrder);

        for (CoffeeOrderItem item : order.getItems()) {
            item.setCoffeeOrder(order);
        }

        itemDao.saveAll(order.getItems());

        return order;
    }

}
