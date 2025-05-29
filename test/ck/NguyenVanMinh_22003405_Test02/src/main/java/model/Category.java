package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @Column(name = "category_id")
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "category_name")
    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Set<Question> questions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Set<Quiz> quizzes = new LinkedHashSet<>();
}
