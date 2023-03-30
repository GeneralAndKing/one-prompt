package one.prompt.service.service.impl;

import lombok.RequiredArgsConstructor;
import one.prompt.service.entity.GlobalConfig;
import one.prompt.service.modal.constant.GlobalConfigKey;
import one.prompt.service.repository.GlobalConfigRepository;
import one.prompt.service.service.GlobalConfigService;
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
