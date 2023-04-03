package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import one.prompt.entity.Category;
import one.prompt.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author klein
 */
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;


    @GetMapping("")
    public ResponseEntity<List<Category.HomeCategory>> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll().stream().map(Category::toHomeCategory).toList());
    }
}
