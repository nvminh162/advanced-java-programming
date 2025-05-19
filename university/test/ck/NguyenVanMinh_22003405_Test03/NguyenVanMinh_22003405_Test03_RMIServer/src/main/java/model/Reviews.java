package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
@Entity
@Table(name = "reviews")
@IdClass(Reviews.ReviewId.class)
public class Reviews implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "ISBN")
    @EqualsAndHashCode.Include
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    @EqualsAndHashCode.Include
    private Person person;

    private int rating;
    private String comment;

    public static class ReviewId implements Serializable {
        private String book;
        private int person;
    }
}