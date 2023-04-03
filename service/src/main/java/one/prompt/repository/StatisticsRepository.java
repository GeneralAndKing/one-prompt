package one.prompt.repository;

import one.prompt.base.BaseRepository;
import one.prompt.entity.Statistics;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author klein
 */
@Repository
public interface StatisticsRepository extends BaseRepository<Statistics> {

    @Modifying
    @Query(value = "UPDATE statistics SET liked=liked+1 WHERE prompt_id=:promptId", nativeQuery = true)
    void likeByPromptId(Long promptId);

    @Modifying
    @Query(value = "UPDATE statistics SET watch=watch+1 WHERE prompt_id=:promptId", nativeQuery = true)
    void watchByPromptId(Long promptId);
}
