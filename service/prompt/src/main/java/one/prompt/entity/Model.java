package one.prompt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
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
@Builder
@Entity
@ToString(callSuper = true)
@Accessors(chain = true)
@Table(name = "model")
@Where(clause = "deleted = false")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE model SET deleted=true WHERE id=?")
@NoArgsConstructor
@AllArgsConstructor
public class Model extends BaseEntity<Model> {

    @Column(name = "name", columnDefinition = "varchar(255)")
    @org.hibernate.annotations.Comment("Data name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    String description;

    @Column(name = "logo", columnDefinition = "varchar(255)")
    String logo;


    @ToString.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Category> categories;
}
