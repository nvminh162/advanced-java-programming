package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "staffs")
@AttributeOverride(name = "id", column = @Column(name = "staff_id"))
public class Staff extends Person {
    @Column(columnDefinition = "TINYINT")
    private byte active;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Staff manager;

    @ToString.Exclude
    @OneToMany(mappedBy = "manager")
    private List<Staff> staffs = new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}