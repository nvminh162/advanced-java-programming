package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "ticketNumber")
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {
    @Id
    @Column(name = "ticket_number")
    private String ticketNumber;

    private String seat;

    @Enumerated(EnumType.STRING)
    private Type type;

    private double price;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    private enum Type {
        VIP, STANDARD
    }
}
