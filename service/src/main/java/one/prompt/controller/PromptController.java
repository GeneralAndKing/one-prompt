package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import one.prompt.context.MockSecretContextHolder;
import one.prompt.entity.Comment;
import one.prompt.entity.Prompt;
import one.prompt.entity.SysUser;
import one.prompt.entity.projection.PromptComplete;
import one.prompt.model.dto.CommentRequest;
import one.prompt.repository.PromptRepository;
import one.prompt.service.PromptService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author klein
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/prompt")
public class PromptController {

  private final PromptRepository promptRepository;

  private final PromptService promptService;


  @GetMapping
  ResponseEntity<Page<PromptComplete>> getPublicPromptsByIdAndTagNames(
      @RequestParam(required = false, defaultValue = "") List<Long> categoryIds,
      @RequestParam(required = false, defaultValue = "") String name,
      @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(promptService.getPublicPromptsByCategoryIds(name, categoryIds, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getPromptById(@PathVariable Long id) {
    SysUser sysUser = MockSecretContextHolder.getCurrentUser();
    Prompt prompt = promptRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Can't found prompt id : %d", id)));
    if (Boolean.TRUE.equals(promptRepository.existsPromptByIdAndUserLiked(prompt.getId(), sysUser.getId()))) {
      return ResponseEntity.ok(prompt.toLikedPrompt());
    } else {
      return ResponseEntity.ok(prompt.toUnlikedPrompt());
    }
  }

  @PostMapping("/{id}/comment")
  public ResponseEntity<Comment> comment(@PathVariable Long id, @RequestBody CommentRequest request) {

    return null;
  }

  @PostMapping("/{id}/like")
  public ResponseEntity<?> like(@PathVariable Long id) {
    return null;
  }

  @PostMapping("/{id}/watch")
  public ResponseEntity<?> watch(@PathVariable Long id) {
    return null;
  }
}
