package one.prompt.service.impl;

import lombok.RequiredArgsConstructor;
import one.prompt.dto.CommentRequest;
import one.prompt.dto.PromptRequest;
import one.prompt.entity.*;
import one.prompt.repository.CategoryRepository;
import one.prompt.repository.CommentRepository;
import one.prompt.repository.PromptRepository;
import one.prompt.repository.TagRepository;
import one.prompt.service.PromptService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author klein
 */
@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final PromptRepository promptRepository;

    @Override
    public Comment comment(Long id, CommentRequest request) {
        Prompt prompt = promptRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Can't found prompt id: %d", id)));
        return commentRepository.save(Comment.builder().content(request.getContent()).title(request.getTitle()).verified(false).prompt(prompt).build());
    }

    @Override
    public Prompt add(PromptRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Can't found category id : %d", request.getCategoryId())));
        List<Tag> tags = tagRepository.findAllById(request.getTagIds());
        if (tags.size() != request.getTagIds().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The tag list does not match expectations.");
        }
        return promptRepository.save(Prompt.builder()
                .name(request.getName())
                .description(request.getDescription())
                .logo(request.getLogo())
                .model(request.getModel())
                .images(request.getImages())
                .category(category)
                .content(request.getContent())
                .tags(tags)
                .content(request.getContent())
                .instructions(request.getInstructions())
                .verified(false)
                .statistics(Statistics.builder().build())
                .build());
    }


}
