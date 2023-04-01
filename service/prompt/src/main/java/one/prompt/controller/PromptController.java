package one.prompt.controller;

import one.prompt.dto.CommentRequest;
import one.prompt.entity.Comment;
import one.prompt.entity.Prompt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author klein
 */
@RestController
@RequestMapping("/prompt")
public class PromptController {


    @GetMapping("/{id}")
    public ResponseEntity<Prompt> getPromptById(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<Comment> uploadComments(@PathVariable Long id, @RequestBody CommentRequest request) {

        return null;
    }
}
