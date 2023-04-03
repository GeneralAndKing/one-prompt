package one.prompt.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import one.prompt.base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author klein
 */

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@Accessors(chain = true)
@Table(name = "statistics")
@Where(clause = "deleted = false")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE statistics SET deleted=true WHERE id=?")
public class Statistics extends BaseEntity<Statistics> {
    @OneToOne
    @JoinColumn(name = "prompt_id")
    private Prompt prompt;

    @Column(name = "liked", columnDefinition = "bigint default 0")
    private Long liked;

    @Column(name = "watch", columnDefinition = "bigint default 0")
    private Long watch;

    @Column(name = "star", columnDefinition = "float default 0")
    private Float star;
}
