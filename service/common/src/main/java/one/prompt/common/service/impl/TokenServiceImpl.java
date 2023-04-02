package one.prompt.common.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import one.prompt.common.entity.SysUser;
import one.prompt.common.model.UserToken;
import one.prompt.common.model.UserTokenInfo;
import one.prompt.common.repository.cache.UserTokenRepository;
import one.prompt.common.service.TokenService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;

import static one.prompt.common.context.DatasourceCacheContextHolder.GlobalConfigHolder.getBoolean;
import static one.prompt.common.model.constant.GlobalConfigKey.AUTHENTICATION_ONCE;

/**
 * 2023/4/2 23:01:27
 *
 * @author yue
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

  private final JwtEncoder jwtEncoder;
  private final JwtDecoder jwtDecoder;
  private final UserTokenRepository userTokenRepository;

  @Override
  public UserToken buildToken(SysUser user) {

    Instant now = Instant.now();
    UserToken userToken = generateNewUserToken(user, now);
    Optional<UserToken> existTokenOptional = userTokenRepository.findById(user.getWechatId());
    Boolean onlyOnceOrNoCache = getBoolean(AUTHENTICATION_ONCE) || existTokenOptional.isEmpty();
    if (Boolean.TRUE.equals(onlyOnceOrNoCache)) {
      userTokenRepository.deleteById(user.getWechatId());
      userTokenRepository.save(userToken);
      return userToken;
    }
    UserToken existToken = existTokenOptional.get();
    Instant expiresAt = jwtDecoder.decode(existToken.getRefreshToken()).getExpiresAt();
    if (Objects.isNull(expiresAt) || expiresAt.isBefore(now)) {
      userTokenRepository.deleteById(user.getName());
      userTokenRepository.save(userToken);
      return userToken;
    }
    return existToken;
  }


  private UserToken generateNewUserToken(SysUser user, Instant now) {
    return new UserToken()
        .setAccessToken(buildAccessToken(user, now))
        .setRefreshToken(buildRefreshToken(user, now))
        .setId(user.getName())
        .setSubject(user.getName())
        .setUsername(user.getUsername());
  }

  private String buildRefreshToken(SysUser user, Instant now) {
    return buildToken(user, now,
        now.plus(30,  ChronoUnit.DAYS),
        claim -> claim.putAll(Collections.emptyMap())
    );
  }

  private String buildAccessToken(SysUser user, Instant now) {
    return buildToken(user, now,
        now.plus(7, ChronoUnit.DAYS),
        claim -> claim.putAll(buildTokenInfo(user))
    );
  }

  private String buildToken(SysUser user, Instant now, Instant expires, Consumer<Map<String, Object>> claimsConsumer) {
    JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
        .subject(user.getPhone())
        .issuer("one-prompt")
        .issuedAt(now)
        .expiresAt(expires)
        .notBefore(now)
        .id(user.getWechatId())
        .claims(claimsConsumer)
        .build();
    return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
  }

  /**
   * Build user token info when user authentication.
   *
   * @return this token info
   */
  @SuppressWarnings("unchecked")
  private Map<String, Objects> buildTokenInfo(SysUser user) {
    Set<String> roleNames = Collections.emptySet();
    UserTokenInfo userTokenInfo = new UserTokenInfo()
        .setSubject(user.getName())
        .setUsername(user.getUsername())
        .setRoles(roleNames);
    return new ObjectMapper().convertValue(userTokenInfo, Map.class);
  }
}
