package one.prompt.service;

import one.prompt.entity.SysUser;
import one.prompt.model.UserToken;
import one.prompt.model.constant.ApplicationCache;

/**
 * 目前整个授权流程都是围绕微信来进行的，因此目前来说 {@link SysUser#getWechatId()} 是贯穿整个授权流程的。
 * <p>
 * 2023/4/2 22:35:18
 * <p>
 *
 * @author yue
 */
public interface AuthorizationService {

  /**
   * 小澄夕用户授权，会在 Redis 里面查找 key 为 {@link ApplicationCache.Wechat} + {@code authCode} 的缓存信息
   *
   * @param authCode 授权码
   * @return 令牌信息
   */
  UserToken miniAuth(String authCode);

}
