package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import one.prompt.client.WechatMiniClient;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @param code <a href="https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/wx.login.html">wx.login</a>
 */
record WechatAuthRequest(
    String code
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

  @PostMapping("/auth")
  public HttpEntity<WechatAuthResponse> auth(@RequestBody WechatAuthRequest param) {
    return ok(new WechatAuthResponse(wechatMiniClient.authOpenId(param.code())));
  }

  @PostMapping("/phoneNumber")
  public HttpEntity<WechatPhoneResponse> phoneNumber(@RequestBody WechatAuthRequest param) {
    return ok(new WechatPhoneResponse(wechatMiniClient.getPhoneNumber(param.code())));
  }

}
