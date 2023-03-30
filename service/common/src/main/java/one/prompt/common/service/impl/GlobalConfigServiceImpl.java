package one.prompt.common.service.impl;

import lombok.RequiredArgsConstructor;
import one.prompt.common.modal.constant.GlobalConfigKey;
import one.prompt.common.repository.GlobalConfigRepository;
import one.prompt.common.service.GlobalConfigService;
import one.prompt.common.entity.GlobalConfig;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * 2023/3/31 00:18:10
 *
 * @author yue
 */
@Service
@RequiredArgsConstructor
public class GlobalConfigServiceImpl implements GlobalConfigService {
  private final GlobalConfigRepository globalConfigRepository;

  @Override
  public GlobalConfig getConfig(GlobalConfigKey configKey) {
    return globalConfigRepository
        .findOne(Example.of(new GlobalConfig().setKey(configKey)))
        .orElse(new GlobalConfig().setKey(configKey).setValue(configKey.defaultValue()));
  }
}
