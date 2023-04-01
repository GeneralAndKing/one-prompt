package one.prompt.client;

import lombok.RequiredArgsConstructor;
import one.prompt.common.modal.constant.WechatInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static one.prompt.common.modal.constant.ApplicationCache.Wechat.MINI_ACCESS_TOKEN;
import static one.prompt.common.modal.constant.WechatInfo.GET_ACCESS_TOKEN;
import static one.prompt.common.modal.constant.WechatInfo.Mini.*;

/**
 * 2023/4/1 02:06:31
 *
 * @author yue
 */
@Component
@RequiredArgsConstructor
public class WechatMiniClient {

  private final RestTemplate wechatRestTemplate;
  private final RedisTemplate<String, String> redisTemplate;

  /**
   * Get wechat mini program request access token.
   * It will get from cache, key {@link one.prompt.common.modal.constant.ApplicationCache.Wechat#MINI_ACCESS_TOKEN},
   * and expire is from {@link AccessToken#expiresIn()}. Maximum time is 2 hours(7200 seconds).
   * <p>
   * If application can not get value from cache, it will request from wechat and set to cache.
   * </p>
   *
   * @return mini program request access token
   */
  public String getAccessToken() {
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    String cacheAccessToken = valueOperations.get(MINI_ACCESS_TOKEN.key());
    if (StringUtils.isNoneBlank(cacheAccessToken)) {
      return cacheAccessToken;
    }
    String targetUri = UriComponentsBuilder.fromHttpUrl(GET_ACCESS_TOKEN.url())
        .queryParam("appid", APP_ID.value())
        .queryParam("secret", APP_SECRET.value())
        .queryParam("grant_type", "client_credential")
        .toUriString();
    AccessToken accessToken = wechatRestTemplate.getForObject(targetUri, AccessToken.class);
    if (Objects.isNull(accessToken)) {
      // TODO: It should throw exception.
      return "微信令牌请求失败";
    }
    if (StringUtils.isEmpty(accessToken.accessToken())) {
      // TODO: It should throw exception.
      return WechatInfo.Mini.errorMessage(accessToken.errorCode());
    }
    valueOperations.set(MINI_ACCESS_TOKEN.key(), accessToken.accessToken(), accessToken.expiresIn(), TimeUnit.SECONDS);
    return accessToken.accessToken();
  }

  /**
   * Get user open id.
   *
   * @param code <a href="https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/wx.login.html">wx.login</a>
   * @return user mini program open id.
   * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-login/code2Session.html">小程序登陆</a>
   */
  public String authOpenId(String code) {
    String targetUri = UriComponentsBuilder.fromHttpUrl(AUTH.url())
        .queryParam("appid", APP_ID.value())
        .queryParam("secret", APP_SECRET.value())
        .queryParam("js_code", code)
        .queryParam("grant_type", "authorization_code")
        .toUriString();
    AuthResponse authResponse = wechatRestTemplate.getForObject(targetUri, AuthResponse.class);
    if (Objects.isNull(authResponse)) {
      // TODO: It should throw exception.
      return "微信请求失败";
    }
    if (StringUtils.isEmpty(authResponse.openid())) {
      // TODO: It should throw exception.
      return WechatInfo.Mini.errorMessage(authResponse.errorCode());
    }
    return authResponse.openid();
  }

  /**
   * Get user phone number.
   *
   * @param code <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/getPhoneNumber.html">获取手机号 code</a>
   * @return phone number
   * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-info/phone-number/getPhoneNumber.html">获取手机号</a>
   */
  public String getPhoneNumber(String code) {
    String targetUri = UriComponentsBuilder.fromHttpUrl(GET_PHONE_NUMBER.url())
        .queryParam("access_token", getAccessToken())
        .toUriString();
    PhoneResponse response = wechatRestTemplate.postForObject(targetUri, new PhoneRequest(code), PhoneResponse.class);
    if (Objects.isNull(response) || Objects.isNull(response.phoneInfo())) {
      // TODO: It should throw exception.
      return "微信请求失败";
    }
    if (StringUtils.isEmpty(response.phoneInfo().phoneNumber())) {
      // TODO: It should throw exception.
      return WechatInfo.Mini.errorMessage(response.errorCode());
    }
    return response.phoneInfo().phoneNumber();
  }
}
