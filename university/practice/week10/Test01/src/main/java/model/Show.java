package model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shows", indexes = {
    @Index(name = "idx_show_datetime", columnList = "show_date_time")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"movie", "tickets"})
@EqualsAndHashCode(of = "id")
public class Show implements Serializable {
    @Id
    @Column(name = "show_id")
    private String id;
    
    @Column(name = "show_date_time")
    private LocalDateTime showDateTime;
    
    @Column(name = "hall_name")
    private String hallName;
    
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();
    
    // Helper method to add tickets
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setShow(this);
    }
    
    // Helper method to check if show has any tickets booked
    public boolean hasBookings() {
        return !tickets.isEmpty();
    }
}
