package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "stores")
public class Store implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int id;

    @Embedded
    private Address address;

    @Embedded
    private Contact contact;

    @Column(name = "store_name")
    private String name;

    @OneToMany(mappedBy = "store")
    @ToString.Exclude
    private List<Staff> staffs;

    @OneToMany(mappedBy = "store")
    @ToString.Exclude
    private List<Stock> stocks;
}
