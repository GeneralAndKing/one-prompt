package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import one.prompt.dto.PublicPrompt;
import one.prompt.entity.Category;
import one.prompt.repository.CategoryRepository;
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
        return ResponseEntity.ok(categoryRepository.findAll().stream().map(Category::toHomeRecord).toList());
    }

    @GetMapping("/{id}/prompts")
    ResponseEntity<Page<PublicPrompt>> getPublicPromptsByIdAndTagNames(@PathVariable Long id,
                                                                       @RequestParam Pageable pageable,
                                                                       @RequestParam(name = "tag") List<String> tagNames) {
        return ResponseEntity.ok(categoryService.getPublicPromptsByIdAndTagNames(id, tagNames, pageable));
    }

}