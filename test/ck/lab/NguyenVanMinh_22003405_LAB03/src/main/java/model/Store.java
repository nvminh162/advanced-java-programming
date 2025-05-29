package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "store_name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private Contact contact;

    @OneToMany(mappedBy = "store")
    @ToString.Exclude
    private List<Stock> stocks = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    @ToString.Exclude
    private List<Staff> staffs = new ArrayList<>();
}