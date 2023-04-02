package one.prompt.common.model.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * 2023/3/31 00:05:12
 *
 * @author yue
 */
@Getter
@RequiredArgsConstructor
@Accessors(chain = true, fluent = true)
public enum GlobalConfigKey {
  /**
   * Whether to allow users to log in in only one place.
   */
  AUTHENTICATION_ONCE("true"),
  /**
   * Anonymous role name.
   */
  ANONYMOUS_ROLE_NAME("ROLE_ANONYMOUS");


  /**
   * Config default value.
   */
  private final String defaultValue;

  /**
   * Get config key from key string.
   *
   * @param key key string
   * @return config key
   */
  public static GlobalConfigKey fromKey(String key) {
    for (GlobalConfigKey value : values()) {
      if (StringUtils.equalsIgnoreCase(value.key(), key)) {
        return value;
      }
    }
    return null;
  }

  @JsonValue
  public String key() {
    return name().toLowerCase();
  }

}