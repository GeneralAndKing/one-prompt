package one.prompt.service.handler;

import lombok.extern.slf4j.Slf4j;
import one.prompt.service.context.DatasourceCacheContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 2023/3/31 00:16:23
 *
 * @author yue
 */
@Slf4j
@Component
public class ContextEventHandler {

  @EventListener
  public void handleContextRefreshEvent(ContextRefreshedEvent event) {
    ApplicationContext applicationContext = event.getApplicationContext();
    DatasourceCacheContextHolder.setApplicationContext(applicationContext);
    log.info("DatasourceCacheContextHolder init success.");
  }

}