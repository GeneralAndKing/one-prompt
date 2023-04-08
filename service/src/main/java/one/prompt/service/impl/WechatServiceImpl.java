package one.prompt.service.impl;

import lombok.RequiredArgsConstructor;
import one.prompt.client.WechatMiniClient;
import one.prompt.entity.SysUser;
import one.prompt.model.constant.ApplicationCache;
import one.prompt.repository.SysUserRepository;
import one.prompt.service.WechatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 2023/4/2 21:12:45
 *
 * @author yue
 */
@Service
@RequiredArgsConstructor
public class WechatServiceImpl implements WechatService {

  private final WechatMiniClient wechatMiniClient;
  private final RedisTemplate<String, String> redisTemplate;
  private final SysUserRepository sysUserRepository;

  @Override
  public void login(String wechatCode, String authCode, String openId) {
    String key = ApplicationCache.Wechat.MINI_AUTH_CODE.key() + authCode;
    String auth = redisTemplate.opsForValue().get(key);
    if (StringUtils.isEmpty(auth)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "登陆失败，未获取到登录信息");
    }
    String phoneNumber = wechatMiniClient.getPhoneNumber(wechatCode);
    Optional<SysUser> userOptional = sysUserRepository.findByPhoneAndWechatId(phoneNumber, openId);
    if (StringUtils.isEmpty(openId)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "登录失败，未获取到微信授权信息");
    }
    if (userOptional.isEmpty()) {
      sysUserRepository.save(
          SysUser.builder()
              .phone(phoneNumber)
              .wechatId(openId)
              .build());
    }
    redisTemplate.opsForValue().set(key, phoneNumber + " " + openId, 1, TimeUnit.DAYS);
  }
}
