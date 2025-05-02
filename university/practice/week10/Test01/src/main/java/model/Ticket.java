package model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"customer", "show"})
@EqualsAndHashCode(of = "ticketNumber")
public class Ticket implements Serializable {
    @Id
    @Column(name = "ticket_number")
    private String ticketNumber;
    
    private String seat;
    
    @Enumerated(EnumType.STRING)
    private TicketType type;
    
    private double price;
    
    @Column(name = "booking_date")
    private LocalDate bookingDate;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;
    
    // Enum for ticket type
    public enum TicketType {
        VIP, STANDARD
    }
}
