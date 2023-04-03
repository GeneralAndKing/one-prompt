package one.prompt.service;


import one.prompt.entity.GlobalConfig;
import one.prompt.model.constant.GlobalConfigKey;

/**
 * 2023/3/31 00:17:06
 *
 * @author yue
 */
public interface GlobalConfigService {

  /**
   * Get global config.
   *
   * @param key config key
   * @return config value
   */
  GlobalConfig getConfig(GlobalConfigKey key);

}
