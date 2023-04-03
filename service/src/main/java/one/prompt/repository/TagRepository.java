package one.prompt.repository;

import one.prompt.base.BaseRepository;
import one.prompt.entity.Tag;
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
