package one.prompt.common.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import one.prompt.common.base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author klein
 */
@Getter
@Setter
@SuperBuilder
@Entity
@ToString(callSuper = true)
@Accessors(chain = true)
@Table(name = "comment")
@Where(clause = "deleted = false")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE comment SET deleted=true WHERE id=?")
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity<Comment> {

    @ManyToOne()
    @JsonManagedReference
    @JoinColumn(name = "prompt_id")
    Prompt prompt;

    @Column(name = "title", columnDefinition = "varchar(255)")
    String title;

    @Column(name = "content", columnDefinition = "text")
    String content;

    @Column(name = "verified", columnDefinition = "boolean default false")
    Boolean verified;
}
