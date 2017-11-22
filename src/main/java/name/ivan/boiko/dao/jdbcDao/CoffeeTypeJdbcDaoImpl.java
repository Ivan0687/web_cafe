package name.ivan.boiko.dao.jdbcDao;

import name.ivan.boiko.dao.CoffeeTypeDao;
import name.ivan.boiko.model.CoffeeType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class CoffeeTypeJdbcDaoImpl extends AbstractGenericJdbcDao<Integer, CoffeeType> implements CoffeeTypeDao {


    CoffeeTypeJdbcDaoImpl(Class<CoffeeType> entityClass) {
        super(entityClass);
    }

    @Override
    public void create(CoffeeType value) {

    }

    @Override
    public CoffeeType read(Integer key) {
        return null;
    }

    @Override
    public Collection<CoffeeType> read() {
        return null;
    }

    @Override
    public void update(Integer key, CoffeeType value) {

    }


}
