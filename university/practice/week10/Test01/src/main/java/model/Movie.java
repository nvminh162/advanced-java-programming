package model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies", indexes = {
    @Index(name = "idx_director", columnList = "director")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "shows")
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
    
    @Column(columnDefinition = "TEXT")
    private String actors;
    
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> shows = new ArrayList<>();
    
    // Helper method to add shows
    public void addShow(Show show) {
        shows.add(show);
        show.setMovie(this);
    }
}
