package one.prompt.common.modal.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 2023/4/1 01:50:59
 *
 * @author yue
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum WechatInfo {
  /**
   * Base URL
   */
  BASE_URL("https://api.weixin.qq.com"),

  /**
   * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/mp-access-token/getAccessToken.html">获取接口调用凭据</a>
   */
  GET_ACCESS_TOKEN("/cgi-bin/token");

  private final String value;

  /**
   * Wechat mini program.
   */
  @Getter
  @Accessors(fluent = true)
  @RequiredArgsConstructor
  public enum Mini {

    /**
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-info/phone-number/getPhoneNumber.html">获取手机号</a>
     */
    GET_PHONE_NUMBER("/wxa/business/getuserphonenumber");

    private final String value;

  }

  /**
   * Wechat official account.
   */
  @Getter
  @RequiredArgsConstructor
  public enum Official {
    ;

    private final String value;

  }

}
