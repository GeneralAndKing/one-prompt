package one.prompt.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/model")
@RequiredArgsConstructor
@Slf4j
public class ModelController {

    private final ModelRepository modelRepository;


    @GetMapping()
    public ResponseEntity<List<Model.HomeModel>> getAll() {
        return ResponseEntity.ok(modelRepository.findAll().stream().map(Model::toHomeModel).toList());
    }

}
