package one.prompt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import one.prompt.base.BaseEntity;
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
@Where(clause = "deleted = false and verified = true")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE prompt SET deleted=true WHERE id=?")
public class Prompt extends BaseEntity<Prompt> {

    @Column(name = "name", columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne()
    @JsonManagedReference
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonManagedReference
    @OneToOne(mappedBy = "prompt", cascade = CascadeType.ALL)
    private Statistics statistics;

    @ManyToOne()
    @JsonManagedReference
    @JoinColumn(name = "model_id")
    private Model model;

    @ToString.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "prompt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @Column(name = "logo", columnDefinition = "varchar(255)")
    private String logo;

    @Type(JsonType.class)
    @Column(name = "images", columnDefinition = "jsonb")
    private List<String> images;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "prompt_tag_relation",
            joinColumns = @JoinColumn(name = "prompt_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    private List<Tag> tags;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "instructions", columnDefinition = "text")
    private String instructions;

    @Column(name = "verified", columnDefinition = "boolean default false")
    Boolean verified;


    @Builder
    @Getter
    public static class PublicPrompt {
        Long id;

        String name;

        String logo;

        Long liked;
        Long watch;

        Float star;
    }

    @SuperBuilder
    @Getter
    public static class UnlikedPrompt {

        Long id;
        String name;

        String logo;

        List<String> images;

        List<Tag> tags;

        Long liked;

        Long watch;

        Float star;
    }

    @SuperBuilder
    @Getter
    public static class LikedPrompt extends UnlikedPrompt {

        String content;

        String instructions;
    }


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
