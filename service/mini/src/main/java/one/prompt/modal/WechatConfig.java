package one.prompt.modal;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Wechat Config.
 *
 * @author yue
 * @version 1.0.0
 */
@ConfigurationProperties("wechat")
public record WechatConfig(
    String appId,
    String appSecret,
    @NestedConfigurationProperty WechatUrl url
) {

  @Data
  public static class WechatUrl {

    /**
     * Mini program base url.
     */
    private String base;

    /**
     * Get user phone number url.
     */
    private String phoneNumber;

  }

}
