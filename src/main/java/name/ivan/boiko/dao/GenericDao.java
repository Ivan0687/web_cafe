package name.ivan.boiko.dao;

import name.ivan.boiko.model.Model;

import java.io.Serializable;
import java.util.Collection;

public interface GenericDao<K extends Serializable, V extends Serializable> {

    void create(V value);

    V read(K key);

    Collection<V> read();

    void update(K key, V value);

    void delete(K key);

}
