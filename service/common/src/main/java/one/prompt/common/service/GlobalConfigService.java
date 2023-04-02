package one.prompt.common.service;

import one.prompt.common.model.constant.GlobalConfigKey;
import one.prompt.common.entity.GlobalConfig;

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
