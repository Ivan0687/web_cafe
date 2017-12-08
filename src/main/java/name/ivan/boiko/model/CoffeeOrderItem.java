package name.ivan.boiko.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table
public class CoffeeOrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private CoffeeOrder coffeeOrder;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private CoffeeType coffeeType;

    @Column
    private int quantity;

    @Column
    private BigDecimal itemCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CoffeeOrder getCoffeeOrder() {
        return coffeeOrder;
    }

    public void setCoffeeOrder(CoffeeOrder coffeeOrder) {
        this.coffeeOrder = coffeeOrder;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }
}
