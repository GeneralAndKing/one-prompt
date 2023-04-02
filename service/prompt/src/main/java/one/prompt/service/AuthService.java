package one.prompt.service;

import one.prompt.common.model.UserToken;

/**
 * 2023/4/2 22:35:18
 *
 * @author yue
 */
public interface AuthService {

  UserToken auth(String authCode);

}
