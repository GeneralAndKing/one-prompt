package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import one.prompt.common.client.WechatMiniClient;
import one.prompt.common.model.UserToken;
import one.prompt.common.model.wechat.MiniCodeRequest;
import one.prompt.service.AuthService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;


/**
 * @param base64Code img base 64 code
 * @param authCode user auth code
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
  private final AuthService authService;
  private static final int AUTH_CODE_COUNT = 8;

  @GetMapping("/code")
  public HttpEntity<MiniCodeResponse> miniAuthCode() {
    String authCode = RandomStringUtils.randomNumeric(AUTH_CODE_COUNT);
    return ok(
        new MiniCodeResponse(
            wechatMiniClient.getQrCode(new MiniCodeRequest(
                "authCode=" + authCode,
                "pages/auth/auth",
                false,
                "develop",
                320
            ), authCode),
            authCode
        )
    );
  }

  @PostMapping("/wechat")
  public HttpEntity<UserToken> wechat(@RequestBody MiniAuthRequest request) {
    return ok(authService.auth(request.authCode()));
  }

}
