package one.prompt.model.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * @author klein
 */
@SuperBuilder
@Getter
public class LikedPrompt extends UnlikedPrompt {

    String content;

    String instructions;
}
