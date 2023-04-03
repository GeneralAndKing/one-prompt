package one.prompt.model.dto;

import lombok.Builder;
import lombok.Getter;


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
