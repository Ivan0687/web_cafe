package name.ivan.boiko.servlets;

import com.google.gson.Gson;
import name.ivan.boiko.dao.CoffeeTypeDao;
import name.ivan.boiko.dao.jdbcDao.CoffeeTypeJdbcDaoImpl;
import name.ivan.boiko.model.CoffeeType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/coffeelist")
public class CoffeeListServlet extends HttpServlet {

//    private CoffeeTypeDao dao = new CoffeeTypeJdbcDaoImpl();

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//        ArrayList<CoffeeType> coffeeTypes = (ArrayList<CoffeeType>) dao.read();

        ArrayList<CoffeeType> coffeeTypes = new ArrayList<>();

        CoffeeType coffeeType = new CoffeeType();
        coffeeType.setId(1);
        coffeeType.setName("coffee type");
        coffeeType.setPrice(new BigDecimal(32.65).round(new MathContext(4)));

        coffeeTypes.add(coffeeType);

        String coffeeTypesJson = gson.toJson(coffeeTypes);

        req.setAttribute("coffeeItems", coffeeTypes);


//        resp.setHeader("coffeeItems", coffeeTypesJson);

        req.getRequestDispatcher("coffeelist.jsp").forward(req,resp);
    }
}
