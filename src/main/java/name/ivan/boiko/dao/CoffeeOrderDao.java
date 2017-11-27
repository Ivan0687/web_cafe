package name.ivan.boiko.dao;

import name.ivan.boiko.model.CoffeeOrder;

public interface CoffeeOrderDao extends GenericDao<Integer, CoffeeOrder> {

    CoffeeOrder saveId(CoffeeOrder value);


}
