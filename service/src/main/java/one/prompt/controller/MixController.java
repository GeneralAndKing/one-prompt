package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import one.prompt.entity.Category;
import one.prompt.entity.Model;
import one.prompt.repository.CategoryRepository;
import one.prompt.repository.ModelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author klein
 */
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class MixController {

    private final ModelRepository modelRepository;

    @GetMapping("/navigation")
    public ResponseEntity<List<Model.Navigation>> getAllNavigations() {
        return ResponseEntity.ok(modelRepository.findAll().stream().map(Model::toNavigation).toList());
    }

}
