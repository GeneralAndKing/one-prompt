package one.prompt.service.impl;

import lombok.RequiredArgsConstructor;
import one.prompt.entity.SysUser;
import one.prompt.model.UserToken;
import one.prompt.model.constant.ApplicationCache;
import one.prompt.repository.SysUserRepository;
import one.prompt.service.AuthorizationService;
import one.prompt.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * 2023/4/2 22:35:25
 *
 * @author yue
 */
@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

  private final SysUserRepository sysUserRepository;
  private final RedisTemplate<String, String> redisTemplate;
  private final TokenService tokenService;
  private static final int MIN_PHONE_NUMBER = 8;

  @Override
  public UserToken miniAuth(String authCode) {
    String key = ApplicationCache.Wechat.MINI_AUTH_CODE.key() + authCode;
    String value = redisTemplate.opsForValue().get(key);
    if (StringUtils.isBlank(value)) {
      return new UserToken();
    }
    if (StringUtils.equalsIgnoreCase(value, Boolean.TRUE.toString())) {
      // 扫码成功
      return new UserToken().setId(Boolean.TRUE.toString());
    }
    if (value.length() < MIN_PHONE_NUMBER) {
      return new UserToken();
    }
    String phone = StringUtils.substringBefore(value, " ");
    String wechatId = StringUtils.substringAfter(value, " ");
    SysUser user = sysUserRepository.findByPhoneAndWechatId(phone, wechatId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "用户信息不存在"));
    UserToken userToken = tokenService.buildToken(user);
    redisTemplate.delete(key);
    return userToken;
  }
}
