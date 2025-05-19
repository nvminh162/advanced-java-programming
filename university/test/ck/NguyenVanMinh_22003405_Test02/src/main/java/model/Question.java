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
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @Column(name = "question_id")
    @EqualsAndHashCode.Include
    private String id;
    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_level")
    private Level level;

    @Column(name = "question_text")
    private String questionText;

    @OneToMany(mappedBy = "question")
    @ToString.Exclude
    private Set<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "quizzes_questions",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id")
    )
    @ToString.Exclude
    private Set<Quiz> quizzes = new LinkedHashSet<>();

    public enum Type {
        MULTIPLE_CHOICE, TRUE_FALSE, FILL_IN_THE_BLANK, MATCHING, ESSAY
    }

    public enum Level {
        EASY, MEDIUM, HARD
    }
}