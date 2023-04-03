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
    String phone = redisTemplate.opsForValue().get(key);
    if (StringUtils.isBlank(phone)) {
      return new UserToken();
    }
    if (phone.length() < MIN_PHONE_NUMBER) {
      return new UserToken();
    }
    SysUser user = sysUserRepository.findByPhone(phone)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "用户信息不存在"));
    UserToken userToken = tokenService.buildToken(user);
    redisTemplate.delete(key);
    return userToken;
  }
}
