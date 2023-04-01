package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import one.prompt.entity.Category;
import one.prompt.entity.Model;
import one.prompt.repository.CategoryRepository;
import one.prompt.repository.ModelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author klein
 */
@RestController
@RequestMapping("/model")
@RequiredArgsConstructor
public class ModelController {

    private final ModelRepository modelRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping()
    public ResponseEntity<List<Model>> getAll() {
        return ResponseEntity.ok(modelRepository.findAll());
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<List<Category>> getAllCategoriesById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryRepository.findAllByModelId(id));
    }
}
