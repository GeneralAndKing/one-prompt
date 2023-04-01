package one.prompt.repository;

import one.prompt.common.base.BaseRepository;
import one.prompt.entity.Comment;
import org.springframework.stereotype.Repository;

/**
 * @author klein
 */
@Repository
public interface CommentRepository extends BaseRepository<Comment> {
}
