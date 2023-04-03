package one.prompt.repository;

import one.prompt.base.BaseRepository;
import one.prompt.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author klein
 */
@Repository
public interface CategoryRepository extends BaseRepository<Category> {

    Optional<Category> findByName(String name);

    List<Category> findAllByModelId(Long modelId);
}
