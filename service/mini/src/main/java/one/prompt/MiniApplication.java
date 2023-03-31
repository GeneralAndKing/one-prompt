package one.prompt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Spring Application.
 *
 * @author yue
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class MiniApplication {

  public static void main(String[] args) {
    SpringApplication.run(MiniApplication.class, args);
  }

}
