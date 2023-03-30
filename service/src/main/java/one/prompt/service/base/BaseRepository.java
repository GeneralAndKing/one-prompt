package one.prompt.service.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 2023/3/31 00:18:53
 *
 * @author yue
 */
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<E>> extends JpaRepository<E, Long>, QuerydslPredicateExecutor<E> {

}