package name.ivan.boiko.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table
public class CoffeeOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Timestamp orderDate;

    @Column
    private String name;

    @Column
    private String deliveryAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coffeeOrder")
    private List<CoffeeOrderItem> items;

    @Column
    private BigDecimal cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
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

    public List<CoffeeOrderItem> getItems() {
        return items;
    }

    public void setItems(List<CoffeeOrderItem> items) {
        this.items = items;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

}
