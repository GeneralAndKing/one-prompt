package one.prompt.common.repository;

import one.prompt.common.base.BaseRepository;
import one.prompt.common.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author klein
 */
@Repository
public interface SysUserRepository extends BaseRepository<SysUser> {
  Optional<SysUser> findByPhone(String phone);

  Optional<SysUser> findByPhoneAndWechatId(String phone, String wechatId);

}
