package name.ivan.boiko.servlets;

import name.ivan.boiko.Service.CoffeeService;
import name.ivan.boiko.Service.PooledDataSource;
import name.ivan.boiko.dao.CoffeeOrderDao;
import name.ivan.boiko.dao.jdbcDao.CoffeeOrderJdbcDaoImpl;
import name.ivan.boiko.model.CoffeeOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private DataSource dataSource = PooledDataSource.getDataSource();

    private CoffeeService service = new CoffeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CoffeeOrder order = (CoffeeOrder) req.getSession().getAttribute("order");

        order.setName(req.getParameter("name"));
        order.setDeliveryAddress(req.getParameter("address"));

        order = service.saveOrder(order);

        req.getSession().setAttribute("order", order);

        req.getRequestDispatcher("order.jsp").forward(req,resp);
    }
}
