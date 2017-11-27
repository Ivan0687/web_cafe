package name.ivan.boiko.dao;

import name.ivan.boiko.model.CoffeeOrderItem;

import java.util.List;

public interface CoffeeOrderItemDao extends GenericDao<Integer, CoffeeOrderItem> {

    List<CoffeeOrderItem> readAllByOrderId(Integer orderId);
    
    void saveAll(List<CoffeeOrderItem> items);
}
