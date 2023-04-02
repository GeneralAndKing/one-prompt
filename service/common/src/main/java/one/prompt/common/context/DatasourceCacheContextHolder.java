package one.prompt.common.context;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import one.prompt.common.entity.GlobalConfig;
import one.prompt.common.model.constant.GlobalConfigKey;
import one.prompt.common.service.GlobalConfigService;
import org.springframework.context.ApplicationContext;

/**
 * 2023/3/31 00:15:38
 *
 * @author yue
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatasourceCacheContextHolder {

  private static ApplicationContext applicationContext;

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public static void setApplicationContext(ApplicationContext applicationContext) {
    DatasourceCacheContextHolder.applicationContext = applicationContext;
  }

  /**
   * Get global config holder.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class GlobalConfigHolder {

    public static GlobalConfig getConfig(GlobalConfigKey key) {
      return getService().getConfig(key);
    }

    public static Boolean getBoolean(GlobalConfigKey key) {
      return getConfig(key).getBooleanValue();
    }

    public static Integer getInt(GlobalConfigKey key) {
      return getConfig(key).getIntValue(key);
    }

    public static String getValue(GlobalConfigKey key) {
      return getConfig(key).getValue();
    }

    private static GlobalConfigService getService() {
      return getApplicationContext().getBean(GlobalConfigService.class);
    }

  }
}