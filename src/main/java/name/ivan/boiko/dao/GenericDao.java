package name.ivan.boiko.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GenericDao<K extends Serializable, V> extends JpaRepository<V, K> {

}
