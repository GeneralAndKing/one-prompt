package one.prompt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import one.prompt.base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author klein
 */
@Getter
@Setter
@Entity
@ToString(callSuper = true)
@Accessors(chain = true)
@Table(name = "tag")
@Where(clause = "deleted = false")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE tag SET deleted=true WHERE id=?")
public class Tag extends BaseEntity<Tag> {
    @Column(name = "name", columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "color", columnDefinition = "varchar(255)")
    private String color;


    @Column(name = "verified", columnDefinition = "boolean default false")
    private Boolean verified;
}
