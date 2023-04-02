package one.prompt.common.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

import java.time.Duration;
import java.util.UUID;

/**
 * 2023/4/2 23:06:06
 *
 * @author yue
 */
@Configuration
public class JwtConfig {

  private final RSAKey jwk;
  public JwtConfig() throws JOSEException {
    jwk = new RSAKeyGenerator(2048)
        .keyUse(KeyUse.SIGNATURE)
        .keyID(UUID.randomUUID().toString())
        .generate();
  }



  /**
   * Decode user info from Jwt token.
   *
   * @return Jwt decoder
   * @throws JOSEException {@link RSAKey#toRSAPublicKey()} throws reason.
   */
  @Bean
  public JwtDecoder jwtDecoder() throws JOSEException {
    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(jwk.toRSAPublicKey()).build();
    // Add validators.
    OAuth2TokenValidator<Jwt> withClockSkew = new DelegatingOAuth2TokenValidator<>(
        new JwtTimestampValidator(Duration.ZERO),
        new JwtIssuerValidator("one-prompt"));
    jwtDecoder.setJwtValidator(withClockSkew);
    return jwtDecoder;
  }

  /**
   * Encode user info to Jwt token.
   *
   * @return Encoder.
   */
  @Bean
  public JwtEncoder jwtEncoder() {
    return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwk)));
  }

}
