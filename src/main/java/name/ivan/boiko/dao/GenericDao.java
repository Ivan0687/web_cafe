package name.ivan.boiko.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<K extends Serializable, V extends Serializable> {

    void save(V value);

    V read(K key);

    List<V> readAll();

    void update(V value);

    void delete(K key);

}
