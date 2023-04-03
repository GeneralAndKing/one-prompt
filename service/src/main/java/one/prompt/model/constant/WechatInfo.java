package one.prompt.model.constant;

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

  public String url() {
    return BASE_URL.value + value;
  }

  /**
   * Wechat mini program.
   */
  @Getter
  @Accessors(fluent = true)
  @RequiredArgsConstructor
  public enum Mini {

    /**
     * App id
     */
    APP_ID("wx0286ac4d19920db2"),

    /**
     * App secret
     */
    APP_SECRET("2f42f6052d342f7527a058e1a769c7f8"),

    /**
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-login/code2Session.html">小程序登陆</a>
     */
    AUTH("/sns/jscode2session"),

    /**
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-info/phone-number/getPhoneNumber.html">获取手机号</a>
     */
    GET_PHONE_NUMBER("/wxa/business/getuserphonenumber"),

    /**
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/qrcode-link/qr-code/getUnlimitedQRCode.html">获取不限制的小程序码</a>
     */
    GET_UN_LIMITED_QR_CODE("/wxa/getwxacodeunlimit");

    private final String value;

    public String url() {
      return BASE_URL.value + value;
    }

    public static String errorMessage(Integer errorCode) {
      return errorCode + switch (errorCode) {
        case -1 -> "系统繁忙，请稍候再试";
        case 0 -> "请求成功";
        case 40001 -> "AppSecret错误或者AppSecret不属于这个应用，请开发者确认AppSecret的正确性";
        case 40002 -> "请确保grant_type字段值为client_credential";
        case 40013 -> "不合法的 AppID ，请开发者检查 AppID 的正确性，避免异常字符，注意大小写";
        case 40029 -> "js_code无效";
        case 45011 -> "API 调用太频繁，请稍候再试";
        // https://developers.weixin.qq.com/miniprogram/dev/framework/operation.html
        case 40226 -> "高风险等级用户，小程序登录拦截 。风险等级详见用户安全解方案";
        case 40164 -> "调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置。";
        case 89503 -> "此IP调用需要管理员确认,请联系管理员";
        case 89501 -> "此IP正在等待管理员确认,请联系管理员";
        case 89506 -> "24小时内该IP被管理员拒绝调用两次，24小时内不可再使用该IP调用";
        case 89507 -> "1小时内该IP被管理员拒绝调用一次，1小时内不可再使用该IP调用";
        default -> "未知错误";
      };
    }
  }

  /**
   * Wechat official account.
   */
  @Getter
  @RequiredArgsConstructor
  public enum Official {
    ;

    private final String value;

    public String url() {
      return BASE_URL.value + value;
    }
  }

}
