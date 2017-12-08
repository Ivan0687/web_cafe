package name.ivan.boiko.services;

import name.ivan.boiko.dao.CoffeeOrderDao;
import name.ivan.boiko.dao.CoffeeOrderItemDao;
import name.ivan.boiko.dao.CoffeeTypeDao;
import name.ivan.boiko.dao.ConfigurationDao;
import name.ivan.boiko.model.CoffeeOrder;
import name.ivan.boiko.model.CoffeeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoffeeService {

    private final CoffeeTypeDao typeDao;
    private final CoffeeOrderItemDao itemDao;
    private final CoffeeOrderDao orderDao;
    private final ConfigurationDao configurationDao;

    @Autowired
    public CoffeeService(CoffeeTypeDao typeDao, CoffeeOrderItemDao itemDao, CoffeeOrderDao orderDao, ConfigurationDao configurationDao) {
        this.typeDao = typeDao;
        this.itemDao = itemDao;
        this.orderDao = orderDao;
        this.configurationDao = configurationDao;
    }

    @Transactional
    public CoffeeOrder saveOrder(CoffeeOrder coffeeOrder){

        CoffeeOrder order = orderDao.save(coffeeOrder);

        return order;
    }

    @Transactional(readOnly = true)
    public List<CoffeeType> getAllEnabled(){
        return typeDao.readAllByDisabledNotLike("Y");
    }

    @Transactional(readOnly = true)
    public List<CoffeeType> getAllCoffee(){
        return typeDao.findAll();
    }

    @Transactional(readOnly = true)
    public int getFreeCupNumber(){
        return configurationDao.findOne("n").getValue();
    }

    @Transactional(readOnly = true)
    public List<CoffeeType> getCoffeeTypes(List<Integer> ids){
        return typeDao.findAll(ids);
    }

}
