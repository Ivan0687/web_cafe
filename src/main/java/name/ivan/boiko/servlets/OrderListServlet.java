package name.ivan.boiko.servlets;

import com.google.gson.Gson;
import name.ivan.boiko.Service.PooledDataSource;
import name.ivan.boiko.dao.CoffeeTypeDao;
import name.ivan.boiko.dao.ConfigurationDao;
import name.ivan.boiko.dao.jdbcDao.CoffeeTypeJdbcDaoImpl;
import name.ivan.boiko.dao.jdbcDao.ConfigurationJdbcDaoImpl;
import name.ivan.boiko.model.CoffeeOrder;
import name.ivan.boiko.model.CoffeeOrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/orderlist")
public class OrderListServlet extends HttpServlet {

    private DataSource dataSource = PooledDataSource.getDataSource();

    private CoffeeTypeDao coffeeTypeDao = new CoffeeTypeJdbcDaoImpl(dataSource);
    private ConfigurationDao configurationDao = new ConfigurationJdbcDaoImpl(dataSource);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> orderItems = req.getParameterMap();

        List<CoffeeOrderItem> coffeeOrderItems = new ArrayList<>();

        for (Map.Entry<String, String[]> item : orderItems.entrySet()) {

            if (!item.getValue()[0].isEmpty()) {

                CoffeeOrderItem orderItem = new CoffeeOrderItem();

                try {
                    orderItem.setCoffeeType(coffeeTypeDao.read(Integer.parseInt(item.getKey())));

                    orderItem.setQuantity(Integer.parseInt(item.getValue()[0]));

                    orderItem.setItemCost(calculateItemCost(orderItem));

                    coffeeOrderItems.add(orderItem);

                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        CoffeeOrder order = new CoffeeOrder();
        order.setItems(coffeeOrderItems);
        order.setCost(calculateOrderCost(coffeeOrderItems));

        req.getSession().setAttribute("order", order);

        req.getRequestDispatcher("orderlist.jsp").forward(req, resp);

    }

    private BigDecimal calculateItemCost(CoffeeOrderItem item){
        int freeCup = configurationDao.read("n").getValue();

        int quantityToPay = item.getQuantity() - item.getQuantity() / freeCup;

        return item.getCoffeeType().getPrice().multiply(new BigDecimal(quantityToPay));
    }

    private BigDecimal calculateOrderCost(List<CoffeeOrderItem> orderItems){

        long freeDelivery = configurationDao.read("x").getValue();
        long delivery = configurationDao.read("m").getValue();

        BigDecimal cost = new BigDecimal(0.00);

        for (CoffeeOrderItem orderItem : orderItems) {

            cost = cost.add(orderItem.getItemCost());
        }

        if (cost.compareTo(BigDecimal.valueOf(freeDelivery)) >= 0){
            return cost;
        } else {
            return cost.add(BigDecimal.valueOf(delivery));
        }

    }
}
