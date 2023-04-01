package one.prompt.repository;

import one.prompt.common.base.BaseRepository;
import one.prompt.entity.Prompt;
import one.prompt.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author klein
 */
@Repository
public interface PromptRepository extends BaseRepository<Prompt> {

    Page<Prompt> findAllByCategoryIdAndTagsIn(Long category_id, Collection<Tag> tags, Pageable pageable);

    Page<Prompt> findAllByCategoryId(Long categoryId, Pageable pageable);


}
