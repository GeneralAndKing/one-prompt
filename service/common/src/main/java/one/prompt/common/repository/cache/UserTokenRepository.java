package one.prompt.common.repository.cache;

import one.prompt.common.base.BaseCacheRepository;
import one.prompt.common.model.UserToken;
import org.springframework.stereotype.Repository;

/**
 * 2023/4/2 23:03:16
 *
 * @author yue
 */
@Repository
public interface UserTokenRepository extends BaseCacheRepository<UserToken> {

}