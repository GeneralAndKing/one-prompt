package one.prompt.common.repository;

import one.prompt.common.base.BaseRepository;
import one.prompt.common.entity.Statistics;
import org.hibernate.annotations.SQLUpdate;
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
