package one.prompt.service;


import one.prompt.entity.Comment;
import one.prompt.entity.Prompt;
import one.prompt.model.dto.CommentRequest;
import one.prompt.model.dto.PromptRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @author klein
 */
public interface PromptService {

    Comment comment(Long id, CommentRequest request);

    void like(Long id);

    void watch(Long id);

    Prompt add(PromptRequest request);

    Page<Prompt.PublicPrompt> getPublicPromptsByCategoryIds(List<Long> categoryIds, Pageable pageable);
}
