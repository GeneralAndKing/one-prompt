package one.prompt.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGlobalConfig is a Querydsl query type for GlobalConfig
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGlobalConfig extends EntityPathBase<GlobalConfig> {

    private static final long serialVersionUID = -1515750961L;

    public static final QGlobalConfig globalConfig = new QGlobalConfig("globalConfig");

    public final one.prompt.common.base.QBaseEntity _super = new one.prompt.common.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    //inherited
    public final StringPath createUser = _super.createUser;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final BooleanPath enabled = _super.enabled;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<one.prompt.common.modal.constant.GlobalConfigKey> key = createEnum("key", one.prompt.common.modal.constant.GlobalConfigKey.class);

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath remark = _super.remark;

    //inherited
    public final NumberPath<Integer> sort = _super.sort;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath updateUser = _super.updateUser;

    public final StringPath value = createString("value");

    public QGlobalConfig(String variable) {
        super(GlobalConfig.class, forVariable(variable));
    }

    public QGlobalConfig(Path<? extends GlobalConfig> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGlobalConfig(PathMetadata metadata) {
        super(GlobalConfig.class, metadata);
    }

}

