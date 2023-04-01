package one.prompt.repository;

import one.prompt.common.base.BaseRepository;
import one.prompt.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author klein
 */
@Repository
public interface CategoryRepository extends BaseRepository<Category> {

    Optional<Category> findByName(String name);
}
