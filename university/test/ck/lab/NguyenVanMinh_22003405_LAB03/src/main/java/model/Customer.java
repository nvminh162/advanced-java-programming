package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "customers")
@AttributeOverride(name = "id", column = @Column(name = "customer_id"))
public class Customer extends Person {

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<Order> orders;

    @Embedded
    private Address address;
}