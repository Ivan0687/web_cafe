package name.ivan.boiko.dao;

import name.ivan.boiko.model.Model;

import java.io.Serializable;
import java.util.Collection;

public interface GenericDao<K extends Serializable, T extends Model> {

    void create(T entity);

    T read(K key);

    Collection<T> read();

    void update(K key, T entity);

    void delete(K key);

}
