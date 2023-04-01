package one.prompt.dto;

import lombok.Builder;
import lombok.Getter;
import one.prompt.entity.Model;

/**
 * @author klein
 */
@Builder
@Getter
public class PublicPrompt {
    Long id;

    String name;

    String logo;

    Long liked;
    Long watch;

    Float star;
}
