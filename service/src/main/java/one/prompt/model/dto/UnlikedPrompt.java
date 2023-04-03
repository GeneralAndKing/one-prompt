package one.prompt.model.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import one.prompt.entity.Tag;

import java.util.List;

/**
 * @author klein
 */
@SuperBuilder
@Getter
public class UnlikedPrompt {

    Long id;
    String name;

    String logo;

    List<String> images;

    List<Tag> tags;

    Long liked;

    Long watch;

    Float star;
}
