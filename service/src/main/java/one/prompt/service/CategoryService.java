package one.prompt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import one.prompt.dto.PublicPrompt;

import java.util.List;

/**
 * @author klein
 */
public interface CategoryService {

    Page<PublicPrompt> getPublicPromptsByIdAndTagNames(Long id, List<String> tagNames, Pageable pageable);
}
