package name.ivan.boiko.servlets;

import name.ivan.boiko.Service.PooledDataSource;
import name.ivan.boiko.dao.CoffeeTypeDao;
import name.ivan.boiko.dao.ConfigurationDao;
import name.ivan.boiko.dao.jdbcDao.CoffeeTypeJdbcDaoImpl;
import name.ivan.boiko.dao.jdbcDao.ConfigurationJdbcDaoImpl;
import name.ivan.boiko.model.CoffeeType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/coffeelist")
public class CoffeeListServlet extends HttpServlet {

    private DataSource dataSource = PooledDataSource.getDataSource();

    private final CoffeeTypeDao coffeeTypeDao = new CoffeeTypeJdbcDaoImpl(dataSource);
    private final ConfigurationDao configurationDao = new ConfigurationJdbcDaoImpl(dataSource);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<CoffeeType> coffeeTypes = coffeeTypeDao.readAllEnabled();

        int freeCup = configurationDao.read("n").getValue();

        req.setAttribute("freeCup", freeCup);

        req.setAttribute("coffeeTypes", coffeeTypes);

        req.getRequestDispatcher("coffeelist.jsp").forward(req,resp);
    }
}
