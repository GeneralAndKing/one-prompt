package one.prompt.common.dto;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import one.prompt.common.entity.Tag;
import org.hibernate.annotations.Type;

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
