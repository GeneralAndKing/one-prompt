package one.prompt.common.repository;

import one.prompt.common.base.BaseRepository;
import one.prompt.common.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author klein
 */
@Repository
public interface TagRepository extends BaseRepository<Tag> {

    List<Tag> findAllByNameIn(Collection<String> name);
}
