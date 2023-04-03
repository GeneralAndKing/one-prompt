package one.prompt.dto;

import lombok.Data;

import java.util.List;

/**
 * @author klein
 */
@Data
public class PromptRequest {

    Long categoryId;

    List<Long> tagIds;

    Long modelId;

    String name;

    String description;

    String logo;

    List<String> images;

    String content;

    String instructions;


}
