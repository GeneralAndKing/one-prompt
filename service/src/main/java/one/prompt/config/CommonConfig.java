package one.prompt.config;

import static org.springframework.security.config.Elements.ANONYMOUS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import one.prompt.model.constant.WechatInfo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

/**
 * 2023/4/1 02:16:46
 *
 * @author yue
 */
@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
@EnableRedisRepositories
public class CommonConfig {

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

  @Bean
  AuditorAware<String> auditorProvider() {
    return () -> {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (Objects.isNull(authentication)) {
        return Optional.of(ANONYMOUS);
      }
      return Optional.ofNullable(authentication.getName());
    };
  }

}
