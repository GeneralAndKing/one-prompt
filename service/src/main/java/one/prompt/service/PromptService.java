package one.prompt.service;

import one.prompt.dto.CommentRequest;
import one.prompt.dto.PromptRequest;
import one.prompt.entity.Comment;
import one.prompt.entity.Prompt;

/**
 * @author klein
 */
public interface PromptService {

    Comment comment(Long id, CommentRequest request);

    void like(Long id);

    void watch(Long id);

    Prompt add(PromptRequest request);
}