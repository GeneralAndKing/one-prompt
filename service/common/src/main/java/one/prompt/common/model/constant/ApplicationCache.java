package one.prompt.common.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 2023/4/1 13:28:08
 *
 * @author yue
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum ApplicationCache {
  ;

  @Getter
  @Accessors(fluent = true)
  @RequiredArgsConstructor
  public enum Wechat {
    /**
     * Wechat
     */
    MINI_ACCESS_TOKEN("application:wechat:mini:accessToken"),
    /**
     * Auth code
     */
    MINI_AUTH_CODE("application:wechat:mini:authCode:");
    private final String key;
  }

}
