package one.prompt.service.impl;

import lombok.RequiredArgsConstructor;
import one.prompt.common.client.WechatMiniClient;
import one.prompt.common.entity.SysUser;
import one.prompt.common.repository.SysUserRepository;
import one.prompt.service.WechatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static one.prompt.common.model.constant.ApplicationCache.Wechat.MINI_AUTH_CODE;

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
    String key = MINI_AUTH_CODE.key() + authCode;
    String auth = redisTemplate.opsForValue().get(key);
    if (StringUtils.isEmpty(auth)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "登陆失败，未获取到登陆信息");
    }
    String phoneNumber = wechatMiniClient.getPhoneNumber(wechatCode);
    Optional<SysUser> userOptional = sysUserRepository.findByPhoneAndWechatId(phoneNumber, openId);
    if (userOptional.isEmpty()) {
      sysUserRepository.save(
          SysUser.builder()
              .phone(phoneNumber)
              .wechatId(openId)
              .build());
    }
    redisTemplate.opsForValue().set(key, phoneNumber, 1, TimeUnit.DAYS);
  }
}
