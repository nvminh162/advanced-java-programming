package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
@Entity
@Table(name = "answers")
public class Answer implements Serializable {
    @Id
    @Column(name = "answer_id")
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}