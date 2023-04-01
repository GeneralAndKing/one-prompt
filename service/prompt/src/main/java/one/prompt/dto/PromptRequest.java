package one.prompt.dto;

import lombok.Data;
import one.prompt.enums.Model;

import java.util.List;

/**
 * @author klein
 */
@Data
public class PromptRequest {

    Long categoryId;

    List<Long> tagIds;

    String name;

    String description;

    String logo;

    List<String> images;

    String content;

    String instructions;

    Model model;
}
