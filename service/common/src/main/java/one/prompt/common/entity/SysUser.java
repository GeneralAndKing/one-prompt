package one.prompt.common.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
@Entity
@ToString(callSuper = true)
@Accessors(chain = true)
@Table(name = "sys_user")
@Where(clause = "deleted = false")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE sys_user SET deleted=true WHERE id=?")
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends BaseEntity<SysUser> {
    @Column(name = "name", columnDefinition = "varchar(255)")
    String name;

    @Column(name = "wechat", columnDefinition = "varchar(255)")
    String wechatId;

    @Column(name = "phone", columnDefinition = "varchar(255)")
    String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_prompt_liked_relation",
            joinColumns = @JoinColumn(name = "sys_user_id"),
            inverseJoinColumns = @JoinColumn(name = "prompt_id"))
    @ToString.Exclude
    List<Prompt> likedPrompts;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_prompt_shared_relation",
            joinColumns = @JoinColumn(name = "sys_user_id"),
            inverseJoinColumns = @JoinColumn(name = "prompt_id"))
    @ToString.Exclude
    List<Prompt> sharedPrompts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_comment_relation",
            joinColumns = @JoinColumn(name = "sys_user_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    @ToString.Exclude
    List<Comment> comments;


}
