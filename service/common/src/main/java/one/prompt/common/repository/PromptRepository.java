package one.prompt.common.repository;

import one.prompt.common.base.BaseRepository;
import one.prompt.common.entity.Prompt;
import one.prompt.common.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

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
