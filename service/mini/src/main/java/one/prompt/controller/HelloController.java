package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import one.prompt.modal.WechatConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello controller.
 *
 * @author yue
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
public class HelloController {

  private final WechatConfig wechatConfig;

  @GetMapping("/hello")
  public String hello() {
    return wechatConfig.url().getBase();
  }

}
