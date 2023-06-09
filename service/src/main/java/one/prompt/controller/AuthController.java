package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import one.prompt.client.WechatMiniClient;
import one.prompt.model.UserToken;
import one.prompt.model.constant.WechatInfo;
import one.prompt.model.wechat.MiniCodeRequest;
import one.prompt.service.AuthorizationService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.ResponseEntity.ok;


/**
 * @param base64Code img base 64 code
 * @param authCode   user auth code
 */
record MiniCodeResponse(
    String base64Code,
    String authCode
) {

}

record MiniAuthRequest(
    String authCode
) {

}

/**
 * 授权流程如下
 * <p>
 * <img src="https://resources.echocow.cn/prompt/auth1.png" />
 * </p>
 * <p>
 * 2023/4/2 15:32:12
 *
 * @author yue
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final WechatMiniClient wechatMiniClient;
  private final AuthorizationService authorizationService;
  private static final int AUTH_CODE_COUNT = 8;

  /**
   * 第一步：
   * 获取授权小程序的二维码与授权码
   *
   * @return 实体
   */
  @GetMapping("/code/{type}")
  public HttpEntity<MiniCodeResponse> miniAuthCode(@PathVariable WechatInfo type) {
    String authCode = RandomStringUtils.randomNumeric(AUTH_CODE_COUNT);
    String base64 = switch (type) {
      case MINI -> wechatMiniClient.getQrCode(new MiniCodeRequest(
          "authCode=" + authCode,
          "pages/auth/auth",
          false,
          "develop",
          320
      ), authCode);
      default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "请求类型错误");
    };
    return ok(new MiniCodeResponse(base64, authCode));
  }

  /**
   * 第二步：
   * 轮询校验用户是否已经在小程序中操作
   *
   * @param request 授权码
   * @return 当已经完成登录时，返回的实体的属性是由值的；未完成时，返回的实体内属性全是空的
   */
  @PostMapping("/mini")
  public HttpEntity<UserToken> miniAuth(@RequestBody MiniAuthRequest request) {
    return ok(authorizationService.miniAuth(request.authCode()));
  }

}
