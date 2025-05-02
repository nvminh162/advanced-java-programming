package model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies", indexes = {
    @Index(name = "idx_director", columnList = "director")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"shows", "actors"})
@EqualsAndHashCode(of = "id")
public class Movie implements Serializable {
    @Id
    @Column(name = "movie_id")
    private String id;
    
    @Column(nullable = false)
    private String title;
    
    private String genre;
    
    @Column(name = "release_year")
    private int releaseYear;
    
    private String director;
    
    private int duration;
    
    @ElementCollection
    @CollectionTable(
        name = "movie_actors", 
        joinColumns = @JoinColumn(name = "movie_id")
    )
    @Column(name = "actor")
    private Set<String> actors = new HashSet<>();
    
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> shows = new ArrayList<>();
    
    // Helper method to add shows
    public void addShow(Show show) {
        shows.add(show);
        show.setMovie(this);
    }
    
    // Helper method to add actor
    public void addActor(String actor) {
        actors.add(actor);
    }
}
