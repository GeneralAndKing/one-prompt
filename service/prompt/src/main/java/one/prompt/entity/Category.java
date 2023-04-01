package one.prompt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import one.prompt.common.base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

/**
 * @author klein
 */
@Getter
@Setter
@Entity
@ToString(callSuper = true)
@Accessors(chain = true)
@Table(name = "category")
@Where(clause = "deleted = false")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE category SET deleted=true WHERE id=?")
public class Category extends BaseEntity<Category> {

    @Column(name = "name", columnDefinition = "varchar(255)")
    @org.hibernate.annotations.Comment("Data name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    String description;

    @ToString.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Prompt> prompts;
}
