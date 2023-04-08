package one.prompt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTag is a Querydsl query type for Tag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTag extends EntityPathBase<Tag> {

    private static final long serialVersionUID = -1852380111L;

    public static final QTag tag = new QTag("tag");

    public final one.prompt.base.QBaseEntity _super = new one.prompt.base.QBaseEntity(this);

    public final StringPath color = createString("color");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    //inherited
    public final StringPath createUser = _super.createUser;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    //inherited
    public final StringPath remark = _super.remark;

    //inherited
    public final NumberPath<Integer> sort = _super.sort;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath updateUser = _super.updateUser;

    public final BooleanPath verified = createBoolean("verified");

    public QTag(String variable) {
        super(Tag.class, forVariable(variable));
    }

    public QTag(Path<? extends Tag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTag(PathMetadata metadata) {
        super(Tag.class, metadata);
    }

}

