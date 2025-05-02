package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"actors", "shows"})
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "movies", indexes = {
        @Index(name = "idx_director", columnList = "director")
})
public class Movie implements Serializable {
    @Id
    @Column(name = "movie_id")
    private String id;

    private String title;
    private String genre;

    @Column(name = "release_year")
    private int releaseYear;

    private String director;
    private int duration;

    @ElementCollection
    @CollectionTable(name = "movie_actors", joinColumns = @JoinColumn(name = "movie_id")
    )
    @Column(name = "actor")
    private Set<String> actors = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> shows;
}
