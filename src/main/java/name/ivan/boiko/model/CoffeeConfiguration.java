package name.ivan.boiko.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "configuration")
public class CoffeeConfiguration implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private int value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
