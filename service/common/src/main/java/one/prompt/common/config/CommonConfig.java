package one.prompt.common.config;

import lombok.RequiredArgsConstructor;
import one.prompt.common.modal.constant.WechatInfo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 2023/4/1 02:16:46
 *
 * @author yue
 */
@Configuration
@RequiredArgsConstructor
public class CommonConfig {

  @Bean
  RestTemplate wechatRestTemplate() {
    return new RestTemplateBuilder()
        .rootUri(WechatInfo.BASE_URL.value())
        .build();
  }

}
