package name.ivan.boiko.model;

import java.math.BigDecimal;

public class CoffeeType extends Model{

    private int id;

    private String name;

    private BigDecimal price;

    private boolean isDisabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    @Override
    public String toString() {
        return "CoffeeType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isDisabled=" + isDisabled +
                "} ";
    }
}
