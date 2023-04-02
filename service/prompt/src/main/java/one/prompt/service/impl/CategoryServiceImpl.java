package one.prompt.service.impl;

import lombok.RequiredArgsConstructor;
import one.prompt.common.dto.PublicPrompt;
import one.prompt.common.entity.Prompt;
import one.prompt.common.entity.Tag;
import one.prompt.common.repository.PromptRepository;
import one.prompt.common.repository.TagRepository;
import one.prompt.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author klein
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final PromptRepository promptRepository;
    private final TagRepository tagRepository;

    @Override
    public Page<PublicPrompt> getPublicPromptsByIdAndTagNames(Long id, List<String> tagNames, Pageable pageable) {
        Page<Prompt> prompts;
        if (tagNames.size() > 0) {
            List<Tag> tags = tagRepository.findAllByNameIn(tagNames);
            prompts = promptRepository.findAllByCategoryIdAndTagsIn(id, tags, pageable);
        } else {
            prompts = promptRepository.findAllByCategoryId(id, pageable);
        }
        return new PageImpl<>(prompts.getContent().stream().map(Prompt::toPublicPrompt).toList(),
                prompts.getPageable(), prompts.getTotalElements());
    }
}
