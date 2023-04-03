package one.prompt.service;

import one.prompt.entity.SysUser;
import one.prompt.model.UserToken;

/**
 * 2023/4/2 23:01:04
 *
 * @author yue
 */
public interface TokenService {

  /**
   * 构建令牌信息
   *
   * @param user 登录用户
   * @return 令牌
   */
  UserToken buildToken(SysUser user);

}
