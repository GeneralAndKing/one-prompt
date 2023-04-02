package one.prompt.common.service;

import one.prompt.common.entity.SysUser;
import one.prompt.common.model.UserToken;

/**
 * 2023/4/2 23:01:04
 *
 * @author yue
 */
public interface TokenService {

  UserToken buildToken(SysUser user);

}
