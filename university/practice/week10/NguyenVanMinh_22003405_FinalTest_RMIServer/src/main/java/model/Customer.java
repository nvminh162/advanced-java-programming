package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"tickets"})
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id")
    private String id;

    private String name;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    private String phone;
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();
}
