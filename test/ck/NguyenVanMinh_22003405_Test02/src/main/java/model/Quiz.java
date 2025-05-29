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
@Table(name = "quizzes")
public class Quiz implements Serializable {
    @Id
    @Column(name = "quiz_id")
    @EqualsAndHashCode.Include
    private String id;

    private String title;
    private int score;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "quizzes")
    @ToString.Exclude
    private Set<Question> questions = new LinkedHashSet<>();
}