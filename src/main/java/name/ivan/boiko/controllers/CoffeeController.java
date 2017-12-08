package name.ivan.boiko.controllers;

import name.ivan.boiko.model.CoffeeType;
import name.ivan.boiko.services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class CoffeeController {

    private final CoffeeService service;

    @Autowired
    public CoffeeController(CoffeeService service) {
        this.service = service;
    }

    @GetMapping("/coffeelist")
    public ModelAndView typesList() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("coffeelist");

        List<CoffeeType> coffeeTypes = service.getAllCoffee();
        modelAndView.addObject("coffeeTypes", coffeeTypes);

        int freeCup = service.getFreeCupNumber();
        modelAndView.addObject("freeCup", freeCup);

        return modelAndView;
    }

    @GetMapping("/orderlist")
    public ModelAndView orderList(@PathVariable List<String> idStrings) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderlist");

        List<Integer> ids = new ArrayList<>();

        for (String item : idStrings) {

            if (!item.isEmpty()) {

                try {
                    ids.add(Integer.parseInt(item));
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Not valid coffee type id");
                }
            }
        }

        List<CoffeeType> coffeeTypes = service.getCoffeeTypes(ids);


        return modelAndView;
    }

//    --------------------------------------------------------------------------------------

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        Map<String, String[]> orderItems = req.getParameterMap();
//
//        List<CoffeeOrderItem> coffeeOrderItems = new ArrayList<>();
//
//        for (Map.Entry<String, String[]> item : orderItems.entrySet()) {
//
//            if (!item.getValue()[0].isEmpty()) {
//
//                CoffeeOrderItem orderItem = new CoffeeOrderItem();
//
//                try {
//                    orderItem.setCoffeeType(coffeeTypeDao.findOne(Integer.parseInt(item.getKey())));
//
//                    orderItem.setQuantity(Integer.parseInt(item.getValue()[0]));
//
//                    orderItem.setItemCost(calculateItemCost(orderItem));
//
//                    coffeeOrderItems.add(orderItem);
//
//                } catch (NumberFormatException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        }
//
//        CoffeeOrder order = new CoffeeOrder();
//        order.setItems(coffeeOrderItems);
//        order.setCost(calculateOrderCost(coffeeOrderItems));
//
//        req.getSession().setAttribute("order", order);
//
//        req.getRequestDispatcher("orderlist.jsp").forward(req, resp);
//
//    }
//
//    private BigDecimal calculateItemCost(CoffeeOrderItem item){
//        int freeCup = configurationDao.findOne("n").getValue();
//
//        int quantityToPay = item.getQuantity() - item.getQuantity() / freeCup;
//
//        return item.getCoffeeType().getPrice().multiply(new BigDecimal(quantityToPay));
//    }
//
//    private BigDecimal calculateOrderCost(List<CoffeeOrderItem> orderItems){
//
//        long freeDelivery = configurationDao.findOne("x").getValue();
//        long delivery = configurationDao.findOne("m").getValue();
//
//        BigDecimal cost = new BigDecimal(0.00);
//
//        for (CoffeeOrderItem orderItem : orderItems) {
//
//            cost = cost.add(orderItem.getItemCost());
//        }
//
//        if (cost.compareTo(BigDecimal.valueOf(freeDelivery)) >= 0){
//            return cost;
//        } else {
//            return cost.add(BigDecimal.valueOf(delivery));
//        }

    //    --------------------------------------------------------------------------------------

//    @WebServlet(urlPatterns = "/order")
//    public class OrderServlet extends HttpServlet {
//
//        private CoffeeService service = new CoffeeService();
//
//        @Override
//        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//            CoffeeOrder order = (CoffeeOrder) req.getSession().getAttribute("order");
//
//            order.setName(req.getParameter("name"));
//            order.setDeliveryAddress(req.getParameter("address"));
//
//            order = service.saveOrder(order);
//
//            req.getSession().setAttribute("order", order);
//
//            req.getRequestDispatcher("order.jsp").forward(req,resp);
//        }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleIOException(Exception ex) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("message", ex.getMessage());
        return view;
    }

}
