package one.prompt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import one.prompt.base.BaseEntity;
import one.prompt.model.dto.LikedPrompt;
import one.prompt.model.dto.PublicPrompt;
import one.prompt.model.dto.UnlikedPrompt;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

/**
 * @author klein
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Accessors(chain = true)
@Table(name = "prompt")
@Where(clause = "deleted = false")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE prompt SET deleted=true WHERE id=?")
public class Prompt extends BaseEntity<Prompt> {

    @Column(name = "name", columnDefinition = "varchar(255)")
    String name;

    @Column(name = "description", columnDefinition = "text")
    String description;

    @ManyToOne()
    @JsonManagedReference
    @JoinColumn(name = "category_id")
    Category category;

    @JsonManagedReference
    @OneToOne(mappedBy = "prompt", cascade = CascadeType.ALL)
    Statistics statistics;

    @ManyToOne()
    @JsonManagedReference
    @JoinColumn(name = "model_id")
    Model model;

    @ToString.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "prompt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Comment> comments;

    @Column(name = "logo", columnDefinition = "varchar(255)")
    String logo;

    @Type(JsonType.class)
    @Column(name = "images", columnDefinition = "jsonb")
    List<String> images;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "prompt_tag_relation",
            joinColumns = @JoinColumn(name = "prompt_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    List<Tag> tags;

    @Column(name = "content", columnDefinition = "text")
    String content;

    @Column(name = "instructions", columnDefinition = "text")
    String instructions;

    @Column(name = "verified", columnDefinition = "boolean default false")
    Boolean verified;

    public PublicPrompt toPublicPrompt() {
        return PublicPrompt.builder()
                .id(this.getId())
                .name(this.getName())
                .logo(this.getLogo())
                .watch(this.getStatistics().getWatch())
                .star(this.getStatistics().getStar())
                .liked(this.getStatistics().getLiked())
                .build();
    }

    public UnlikedPrompt toUnlikedPrompt() {
        return UnlikedPrompt.builder()
                .id(this.getId())
                .name(this.getName())
                .logo(this.getLogo())
                .tags(this.getTags())
                .images(this.getImages())
                .watch(this.getStatistics().getWatch())
                .star(this.getStatistics().getStar())
                .liked(this.getStatistics().getLiked())
                .build();
    }

    public LikedPrompt toLikedPrompt() {
        return LikedPrompt.builder()
                .id(this.getId())
                .name(this.getName())
                .logo(this.getLogo())
                .tags(this.getTags())
                .images(this.getImages())
                .watch(this.getStatistics().getWatch())
                .star(this.getStatistics().getStar())
                .liked(this.getStatistics().getLiked())
                .build();
    }
}
