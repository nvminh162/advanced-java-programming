package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "staffs")
@AttributeOverride(name = "id", column = @Column(name = "staff_id"))
public class Staff extends Person implements Serializable {
    private byte active;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @ToString.Exclude
    private Store store;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @ToString.Exclude
    private Staff manager;

    @OneToMany(mappedBy = "manager")
    @ToString.Exclude
    private List<Staff> staffs;

    @OneToMany(mappedBy = "staff")
    @ToString.Exclude
    private List<Order> orders;
}
