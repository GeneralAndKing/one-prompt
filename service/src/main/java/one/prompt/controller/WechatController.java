package one.prompt.controller;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import one.prompt.client.WechatMiniClient;
import one.prompt.service.WechatService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import static one.prompt.model.constant.ApplicationCache.Wechat.MINI_AUTH_CODE;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @param code     <a href="https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/wx.login.html">wx.login</a>
 * @param authCode login auth code
 */
record WechatAuthRequest(
    String code,
    String openId,
    String authCode
) {
}

/**
 * @param openId user open id
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-login/code2Session.html">小程序登陆</a>
 */
record WechatAuthResponse(
    String openId
) {
}

/**
 * @param phoneNumber user phone number
 */
record WechatPhoneResponse(
    String phoneNumber
) {
}

/**
 * Hello controller.
 *
 * @author yue
 * @version 1.0.0
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/wechat")
public class WechatController {

  private final WechatMiniClient wechatMiniClient;
  private final WechatService wechatService;
  private final RedisTemplate<String, String> redisTemplate;

  /**
   * 第三步，扫码获取登录用户的 openId
   *
   * @param param 请求参数
   * @return
   */
  @PostMapping("/auth")
  public HttpEntity<WechatAuthResponse> auth(@RequestBody WechatAuthRequest param) {
    String openId = wechatMiniClient.authOpenId(param.code());
    if (StringUtils.isEmpty(param.authCode())) {
      return ok(new WechatAuthResponse(openId));
    }
    String key = MINI_AUTH_CODE.key() + param.authCode();
    String value = redisTemplate.opsForValue().get(key);
    if (Objects.equals(BooleanUtils.toBoolean(value), Boolean.FALSE)) {
      // 扫码成功
      redisTemplate.opsForValue().set(key, Boolean.TRUE.toString());
    }
    return ok(new WechatAuthResponse(openId));
  }

  /**
   * 第四步，点击登录获取用户手机号
   *
   * @param param 参数
   * @return 手机号
   */
  @PostMapping("/phoneNumber")
  public HttpEntity<WechatPhoneResponse> phoneNumber(@RequestBody WechatAuthRequest param) {
    return ok(new WechatPhoneResponse(wechatMiniClient.getPhoneNumber(param.code())));
  }

  /**
   * 第五步，小程序登录成
   *
   * @param param 参数
   * @return 登录成功
   */
  @PostMapping("/login")
  public HttpEntity<String> login(@RequestBody WechatAuthRequest param) {
    wechatService.login(param.code(), param.authCode(), param.openId());
    return ok().build();
  }

}
