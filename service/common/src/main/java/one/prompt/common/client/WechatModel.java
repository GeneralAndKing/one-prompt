package one.prompt.common.client;


import com.fasterxml.jackson.annotation.JsonProperty;
import one.prompt.common.model.constant.WechatInfo;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @param accessToken  Wechat program access token
 * @param expiresIn    Access token expiration time, unit {@link TimeUnit#SECONDS}
 * @param errorCode    {@link WechatInfo.Mini#errorMessage(Integer)}
 * @param errorMessage {@link WechatInfo.Mini#errorMessage(Integer)}
 */
@SuppressWarnings("SpellCheckingInspection")
record AccessToken(
    @JsonProperty("access_token")
    String accessToken,
    @JsonProperty("expires_in")
    Integer expiresIn,
    @JsonProperty("errcode")
    Integer errorCode,
    @JsonProperty("errmsg")
    String errorMessage
) {

}

/**
 * @param errorCode    {@link WechatInfo.Mini#errorMessage(Integer)}
 * @param errorMessage {@link WechatInfo.Mini#errorMessage(Integer)}
 * @param openid       User open ID
 * @param unionId      The unique identifier of the user on the open platform.
 *                     If the current applet has been bound to the WeChat open platform account,
 *                     it will be returned
 * @param sessionKey   Current session key
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/union-id.html">UnionID 机制说明</a>
 */
@SuppressWarnings("SpellCheckingInspection")
record AuthResponse(
    @JsonProperty("errcode")
    Integer errorCode,
    @JsonProperty("errmsg")
    String errorMessage,
    String openid,
    @JsonProperty("unionid")
    String unionId,
    @JsonProperty("session_key")
    String sessionKey) {
}

/**
 * @param phoneNumber     User phone number(Foreign mobile phone numbers will have an country code)
 * @param purePhoneNumber Phone number without country code
 * @param countryCode     country code
 */
record PhoneInfo(
    String phoneNumber,
    String purePhoneNumber,
    String countryCode
) {
}

record PhoneRequest(
    String code
) {
}

record PhoneResponse(
    @JsonProperty("errcode")
    Integer errorCode,
    @JsonProperty("errmsg")
    String errorMessage,
    @JsonProperty("phone_info")
    PhoneInfo phoneInfo) {
}

record MiniCodeResponse(
    byte[] buffer,
    @JsonProperty("errcode")
    Integer errorCode,
    @JsonProperty("errmsg")
    String errorMessage
) {
  @Override
  public String toString() {
    return "MiniCodeResponse{" +
        "buffer=" + Arrays.toString(buffer) +
        ", errorCode=" + errorCode +
        ", errorMessage='" + errorMessage + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof MiniCodeResponse that)) {
      return false;
    }

    return new EqualsBuilder().append(buffer, that.buffer).append(errorCode, that.errorCode).append(errorMessage, that.errorMessage).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(buffer).append(errorCode).append(errorMessage).toHashCode();
  }
}