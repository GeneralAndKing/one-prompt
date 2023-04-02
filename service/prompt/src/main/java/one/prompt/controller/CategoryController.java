package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import one.prompt.common.dto.PublicPrompt;
import one.prompt.common.entity.Category;
import one.prompt.common.entity.Prompt;
import one.prompt.common.repository.CategoryRepository;
import one.prompt.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author klein
 */
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @GetMapping()
    ResponseEntity<List<Category.HomeRecord>> getAll() {
        return ResponseEntity.ok(categoryRepository.findAll().stream().map(Category::ToHomeRecord).toList());
    }

    @GetMapping("/{id}/prompts")
    ResponseEntity<Page<PublicPrompt>> getPublicPromptsByIdAndTagNames(@PathVariable Long id,
                                                                       @RequestParam Pageable pageable,
                                                                       @RequestParam(name = "tag") List<String> tagNames) {
        return ResponseEntity.ok(categoryService.getPublicPromptsByIdAndTagNames(id, tagNames, pageable));
    }

}
