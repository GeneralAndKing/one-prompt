package one.prompt.config;

import lombok.RequiredArgsConstructor;
import one.prompt.model.constant.WechatInfo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 2023/4/1 02:16:46
 *
 * @author yue
 */
@Configuration
@RequiredArgsConstructor
public class ClientConfig {

  @Bean
  RestTemplate wechatRestTemplate() {
    RestTemplate restTemplate = new RestTemplateBuilder()
        .rootUri(WechatInfo.BASE_URL.value())
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.ALL_VALUE)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
    List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
    messageConverters.add(converter);
    return restTemplate;
  }

}
