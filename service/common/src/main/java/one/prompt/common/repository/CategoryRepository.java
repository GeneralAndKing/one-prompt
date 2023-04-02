package one.prompt.common.repository;

import one.prompt.common.base.BaseRepository;
import one.prompt.common.entity.Category;
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
