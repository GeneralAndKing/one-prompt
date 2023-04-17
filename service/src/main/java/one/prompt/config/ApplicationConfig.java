package one.prompt.config;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.security.config.Elements.ANONYMOUS;

/**
 * Application config.
 *
 * @author yue
 * @version 1.0.0
 */
@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
@EnableRedisRepositories
@EnableSpringDataWebSupport
public class ApplicationConfig {

  @PersistenceUnit
  private EntityManagerFactory entityManagerFactory;

  private final EntityManager entityManager;

  /**
   * QueryDSL query factory.
   *
   * @return bean
   */
  @Bean
  JPAQueryFactory jpaQuery() {
    return new JPAQueryFactory(entityManager);
  }


  @Bean
  BlazeJPAQueryFactory blazeJpaQueryFactory() {
    return new BlazeJPAQueryFactory(entityManager, createCriteriaBuilderFactory());
  }

  @Bean
  CriteriaBuilderFactory createCriteriaBuilderFactory() {
    CriteriaBuilderConfiguration config = Criteria.getDefault();
    // do some configuration
    return config.createCriteriaBuilderFactory(entityManagerFactory);
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
