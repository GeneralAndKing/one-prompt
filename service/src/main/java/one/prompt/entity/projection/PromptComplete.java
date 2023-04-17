package one.prompt.entity.projection;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import one.prompt.entity.QPrompt;
import one.prompt.entity.QStatistics;
import one.prompt.entity.QSysUser;

import java.util.List;

/**
 * 2023/4/13 22:58:48
 *
 * @author yue
 */
public record PromptComplete(
    Long id,
    String name,
    String logo,
    List<String> images,
    String description,
    String content,
    String avatar,
    String user,
    Long liked,
    Long watch,
    Float star
) {

  public static ConstructorExpression<PromptComplete> fromQueryDSL(QPrompt prompt, QSysUser user, QStatistics statistics) {
    return
        Projections
            .constructor(PromptComplete.class, prompt.id, prompt.name, prompt.logo, prompt.images,
                prompt.description, prompt.content, user.avatar, user.name, statistics.liked, statistics.watch, statistics.star);
  }
}
