package one.prompt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrompt is a Querydsl query type for Prompt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrompt extends EntityPathBase<Prompt> {

    private static final long serialVersionUID = 1680429421L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrompt prompt = new QPrompt("prompt");

    public final one.prompt.base.QBaseEntity _super = new one.prompt.base.QBaseEntity(this);

    public final QCategory category;

    public final ListPath<Comment, QComment> comments = this.<Comment, QComment>createList("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    //inherited
    public final StringPath createUser = _super.createUser;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<String, StringPath> images = this.<String, StringPath>createList("images", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath instructions = createString("instructions");

    public final StringPath logo = createString("logo");

    public final QModel model;

    public final StringPath name = createString("name");

    //inherited
    public final StringPath remark = _super.remark;

    //inherited
    public final NumberPath<Integer> sort = _super.sort;

    public final QStatistics statistics;

    public final ListPath<Tag, QTag> tags = this.<Tag, QTag>createList("tags", Tag.class, QTag.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath updateUser = _super.updateUser;

    public final BooleanPath verified = createBoolean("verified");

    public QPrompt(String variable) {
        this(Prompt.class, forVariable(variable), INITS);
    }

    public QPrompt(Path<? extends Prompt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrompt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrompt(PathMetadata metadata, PathInits inits) {
        this(Prompt.class, metadata, inits);
    }

    public QPrompt(Class<? extends Prompt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category"), inits.get("category")) : null;
        this.model = inits.isInitialized("model") ? new QModel(forProperty("model")) : null;
        this.statistics = inits.isInitialized("statistics") ? new QStatistics(forProperty("statistics"), inits.get("statistics")) : null;
    }

}

