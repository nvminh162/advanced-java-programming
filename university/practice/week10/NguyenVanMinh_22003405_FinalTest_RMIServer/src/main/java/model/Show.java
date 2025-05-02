package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"tickets"})
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "shows", indexes = {
        @Index(name = "idx_show_date_time", columnList = "show_date_time")
})
public class Show implements Serializable {
    @Id
    @Column(name = "show_id")
    private String id;

    @Column(name = "show_date_time")
    private LocalDateTime showDateTime;

    @Column(name = "hall_name")
    private String hallName;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
