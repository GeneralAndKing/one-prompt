package one.prompt.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import one.prompt.base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
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
public class SysUser extends BaseEntity<SysUser> implements UserDetails {
    @Column(name = "name", columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "avatar", columnDefinition = "varchar(255)")
    private String avatar;

    @Column(name = "wechat_id", columnDefinition = "varchar(255)")
    private String wechatId;

    @Column(name = "phone", columnDefinition = "varchar(255)")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_prompt_liked_relation",
            joinColumns = @JoinColumn(name = "sys_user_id"),
            inverseJoinColumns = @JoinColumn(name = "prompt_id"))
    @ToString.Exclude
    private List<Prompt> likedPrompts;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_prompt_shared_relation",
            joinColumns = @JoinColumn(name = "sys_user_id"),
            inverseJoinColumns = @JoinColumn(name = "prompt_id"))
    @ToString.Exclude
    private List<Prompt> sharedPrompts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_comment_relation",
            joinColumns = @JoinColumn(name = "sys_user_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    @ToString.Exclude
    private List<Comment> comments;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return getPhone();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
