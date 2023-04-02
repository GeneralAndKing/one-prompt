package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import one.prompt.common.client.WechatMiniClient;
import one.prompt.common.model.wechat.MiniCodeRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

/**
 * 2023/4/2 15:32:12
 *
 * @author yue
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final WechatMiniClient wechatMiniClient;
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
            )),
            authCode
        )
    );
  }

}
