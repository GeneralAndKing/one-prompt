package one.prompt.base;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 2023/4/1 13:15:51
 *
 * @author yue
 */
@NoRepositoryBean
public interface BaseCacheRepository<E> extends CrudRepository<E, String> {

}