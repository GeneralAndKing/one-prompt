package one.prompt.modal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("wechat")
public record WechatConfig(
    @DefaultValue(value = "true")
    boolean someProperty,
    boolean someProperty1
) {

}