package one.prompt.common.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * 2023/3/30 23:59:59
 *
 * @author yue
 */
@Getter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings({"unused", "unchecked"})
public class BaseEntity<E extends BaseEntity<E>> {

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Comment("Primary key")
    private Long id;

    /**
     * Entity delete status.
     */
    @Column(nullable = false)
    @Comment("Data deleted. 1 deleted, 0 exist")
    @ColumnDefault("false")
    @Builder.Default
    private Boolean deleted = Boolean.FALSE;


    /**
     * Create time
     */
    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL")
    private LocalDateTime createTime;

    /**
     * Create user name
     */
    @CreatedBy
    @Comment("Create user name")
    private String createUser;

    /**
     * Update time
     */
    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL")
    private LocalDateTime updateTime;

    /**
     * Update user name
     */
    @LastModifiedBy
    @Comment("Update user name")
    private String updateUser;

    /**
     * Remark information.
     */
    private String remark;

    /**
     * Sort data.
     */
    @ColumnDefault("0")
    @Builder.Default
    private Integer sort = 0;

    public E setId(Long id) {
        this.id = id;
        return (E) this;
    }

    public E setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return (E) this;
    }

    public E setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return (E) this;
    }

    public E setCreateUser(String createUser) {
        this.createUser = createUser;
        return (E) this;
    }

    public E setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return (E) this;
    }

    public E setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return (E) this;
    }

    public E setRemark(String remark) {
        this.remark = remark;
        return (E) this;
    }

    public E setSort(Integer sort) {
        this.sort = sort;
        return (E) this;
    }

}