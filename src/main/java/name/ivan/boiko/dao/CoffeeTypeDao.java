package name.ivan.boiko.dao;

import name.ivan.boiko.model.CoffeeType;

import java.util.List;

public interface CoffeeTypeDao extends GenericDao<Integer, CoffeeType> {

    List<CoffeeType> readAllByDisabledNotLike(String s);
}
