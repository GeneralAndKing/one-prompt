package one.prompt.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * @author klein
 */
@OpenAPIDefinition(
        info =
        @Info(
                title = "One-Prompt API Doc",
                description = "",
                version = "0.0.5"),
        servers = {
                @Server(description = "Production env", url = "http://xxxx.com/api/v1"),
                @Server(description = "Test env", url = "http://34.126.105.75:9000"),
                @Server(description = "Development env", url = "http://localhost:9000")
        },
        externalDocs =
        @ExternalDocumentation(
                description = "Project Description",
                url = "https://github.com/GeneralAndKing/one-prompt/blob/master/README.adoc"))
@Configuration
public class OpenAPIConfig {
}
