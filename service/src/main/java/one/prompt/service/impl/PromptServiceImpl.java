package one.prompt.service.impl;

import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import one.prompt.context.MockSecretContextHolder;
import one.prompt.entity.*;
import one.prompt.entity.projection.PromptComplete;
import one.prompt.model.dto.CommentRequest;
import one.prompt.model.dto.PromptRequest;
import one.prompt.repository.*;
import one.prompt.service.PromptService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author klein
 */
@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {
  private final CategoryRepository categoryRepository;
  private final TagRepository tagRepository;
  private final CommentRepository commentRepository;
  private final PromptRepository promptRepository;
  private final SysUserRepository sysUserRepository;
  private final StatisticsRepository statisticsRepository;
  private final BlazeJPAQueryFactory blazeJPAQueryFactory;

  @Transactional
  @Override
  public Comment comment(Long id, CommentRequest request) {
    Prompt prompt = promptRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Can't found prompt id: %d", id)));
    Comment saved = commentRepository.save(Comment.builder().content(request.getContent()).title(request.getTitle()).verified(false).prompt(prompt).build());
    SysUser sysUser = MockSecretContextHolder.getCurrentUser();
    sysUser.getComments().add(saved);
    sysUserRepository.save(sysUser);
    return saved;
  }

  @Transactional
  @Override
  public void like(Long id) {
    Prompt prompt = promptRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Can't found prompt id : %d", id)));
    SysUser sysUser = MockSecretContextHolder.getCurrentUser();
    sysUser.getLikedPrompts().add(prompt);
    sysUserRepository.save(sysUser);
    statisticsRepository.likeByPromptId(id);
  }

  @Override
  public void watch(Long id) {
    statisticsRepository.watchByPromptId(id);
  }

  @Transactional
  @Override
  public Prompt add(PromptRequest request) {
    Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Can't found category id : %d", request.getCategoryId())));
    if (!category.getModel().getId().equals(request.getModelId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Model and category do not match.");
    }
    List<Tag> tags = tagRepository.findAllById(request.getTagIds());
    if (tags.size() != request.getTagIds().size()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The tag list does not match expectations.");
    }
    SysUser sysUser = MockSecretContextHolder.getCurrentUser();
    Prompt saved = promptRepository.save(Prompt.builder()
        .name(request.getName())
        .description(request.getDescription())
        .logo(request.getLogo())
        .model(category.getModel())
        .images(request.getImages())
        .category(category)
        .content(request.getContent())
        .tags(tags)
        .content(request.getContent())
        .instructions(request.getInstructions())
        .verified(false)
        .statistics(Statistics.builder().build())
        .build());
    sysUser.getSharedPrompts().add(saved);
    sysUserRepository.save(sysUser);
    return saved;
  }

  @Override
  public Page<PromptComplete> getPublicPromptsByCategoryIds(String name, List<Long> categoryIds, Pageable pageable) {
    QPrompt prompt = QPrompt.prompt;
    QSysUser user = QSysUser.sysUser;
    QStatistics statistics = QStatistics.statistics;
    BlazeJPAQuery<PromptComplete> select = blazeJPAQueryFactory
        .select(PromptComplete.fromQueryDSL(prompt, user, statistics));
    BooleanBuilder builder = new BooleanBuilder();
    if (StringUtils.isNoneBlank(name)) {
      builder.and(prompt.name.like(name));
    }
    if (CollectionUtils.isNotEmpty(categoryIds)) {
      builder.and(prompt.category.id.in(categoryIds));
    }
    PagedList<PromptComplete> promptCompletes = select
        .from(prompt)
        .leftJoin(prompt.statistics, statistics)
        .leftJoin(user)
        .on(prompt.createUser.eq(user.name))
        .where(builder)
        .orderBy(prompt.id.desc())
        .fetchPage((int) pageable.getOffset(), pageable.getPageSize());
    return new PageImpl<>(promptCompletes.stream().toList(),
        pageable, promptCompletes.getTotalSize());
  }

}
