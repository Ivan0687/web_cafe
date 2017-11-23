package name.ivan.boiko.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class CoffeeOrder extends Model{

    private int id;

    private Date orderDate;

    private String name;

    private String deliveryAddress;

    private Map<CoffeeType, Integer> items;

    private BigDecimal cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
