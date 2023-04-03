package one.prompt.repository;

import one.prompt.base.BaseRepository;
import one.prompt.entity.SysUser;
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
