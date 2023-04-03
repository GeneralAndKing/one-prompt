package one.prompt.common.config;


import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Duration;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 2023/4/1 15:20:36
 *
 * @author yue
 */
@EnableRedisRepositories(basePackages = "one.prompt.common.repository.cache")
@Configuration(proxyBeanMethods = false)
public class CacheConfig {


  private static final ObjectMapper GLOBAL_OBJECT_MAPPER = new ObjectMapper();

  static {
    GLOBAL_OBJECT_MAPPER.activateDefaultTyping(GLOBAL_OBJECT_MAPPER.getPolymorphicTypeValidator(),
        ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    GLOBAL_OBJECT_MAPPER.registerModule(new JavaTimeModule());
  }

  @Bean
  public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
    return builder -> builder
        .cacheDefaults(RedisCacheConfiguration
            .defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(3))
            .serializeKeysWith(fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(fromSerializer(new Jackson2JsonRedisSerializer<>(GLOBAL_OBJECT_MAPPER, Object.class)))
        );
  }

  public static ObjectMapper getGlobalObjectMapper() {
    return GLOBAL_OBJECT_MAPPER;
  }
}
