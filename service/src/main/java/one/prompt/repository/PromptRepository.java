package one.prompt.repository;

import one.prompt.base.BaseRepository;
import one.prompt.entity.Prompt;
import one.prompt.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author klein
 */
@Repository
public interface PromptRepository extends BaseRepository<Prompt> {

    Page<Prompt> findAllByCategoryIdAndTagsIn(Long categoryId, Collection<Tag> tags, Pageable pageable);

    Page<Prompt> findAllByCategoryId(Long categoryId, Pageable pageable);

    @Query(
            value = "SELECT * FROM prompt right join user_prompt_liked_relation uplr on prompt.id = uplr.prompt_id WHERE prompt_id=:id and sys_user_id=:sysUserId",
            nativeQuery = true
    )
    Boolean existsPromptByIdAndUserLiked(Long id, Long sysUserId);


}
