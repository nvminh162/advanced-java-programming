package model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "bookings")
@EqualsAndHashCode(of = "id")
public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id")
    private String id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    
    private String phone;
    private String address;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Ticket> bookings = new ArrayList<>();
    
    // Helper method to add bookings
    public void addBooking(Ticket ticket) {
        bookings.add(ticket);
        ticket.setCustomer(this);
    }
}
