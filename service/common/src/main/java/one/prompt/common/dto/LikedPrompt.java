package one.prompt.common.dto;

import lombok.Builder;
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
