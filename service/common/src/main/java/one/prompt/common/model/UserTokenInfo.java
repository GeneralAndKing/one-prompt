package one.prompt.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import one.prompt.common.entity.SysUser;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * 2023/4/3 00:06:49
 *
 * @author yue
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTokenInfo {

  /**
   * User subject.
   *
   * @see SysUser#getName()
   */
  private String subject;

  /**
   * User name.
   *
   * @see SysUser#getUsername()
   */
  private String username;

  /**
   * Current user roles. (It includes parent roles)
   */
  private Set<String> roles = Collections.emptySet();

//  /**
//   * Current user permissions. (It includes parent roles' permissions)
//   *
//   * @see wiki.lever.service.AuthenticationService#loadUserByUsername(String) set permissions
//   * @see SysUser#getPermissions()
//   */
//  private List<PathPermissionProjection> permissions = Collections.emptyList();

  /**
   * Build token info from {@code JSONObject}.
   * {@link Jwt#getClaim(String)} method he provided can only obtain the basic type,
   * and the rest will be converted into JSONObject, so it needs to be converted manually.
   *
   * @param map claim information
   * @return token info
   */
  public static UserTokenInfo fromMap(Map<String, Object> map) {
    return new ObjectMapper().registerModule(new JavaTimeModule())
        .convertValue(map, UserTokenInfo.class);
  }

}