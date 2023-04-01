package one.prompt.dto;

import lombok.Builder;
import lombok.Getter;
import one.prompt.enums.Model;

/**
 * @author klein
 */
@Builder
@Getter
public class PublicPrompt {
    Long id;

    String name;

    String logo;

    Model model;

    Long liked;
    Long watch;

    Float star;
}
