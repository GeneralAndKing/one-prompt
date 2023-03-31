package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class HelloController {

  @GetMapping("/hello")
  public String hello(String code) {
    return "";
  }

}
