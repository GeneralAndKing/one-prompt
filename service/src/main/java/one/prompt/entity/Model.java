package one.prompt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import one.prompt.base.BaseEntity;
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
    private String description;

    @Column(name = "logo", columnDefinition = "varchar(255)")
    private String logo;


    @ToString.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> categories;


    @Builder
    @Getter
    public static class Navigation {
        @Builder
        @Getter
        public static class Category {
            Long id;
            Integer sort;
            String name;
        }

        Long id;
        Integer sort;
        String name;
        List<Category> categories;
    }

    @Builder
    @Getter
    public static class HomeModel {
        Long id;
        String logo;
        String name;
        String description;
    }

    public Navigation toNavigation() {
        return Navigation.builder()
                .id(this.getId()).name(this.getName()).sort(this.getSort())
                .categories(this.getCategories().stream().map(category -> Navigation.Category.builder().id(category.getId()).name(category.getName()).build()).toList()).build();
    }

    public HomeModel toHomeModel() {
        return HomeModel.builder()
                .id(this.getId())
                .name(this.getName())
                .logo(this.getLogo())
                .description(this.getDescription())
                .build();
    }
}
