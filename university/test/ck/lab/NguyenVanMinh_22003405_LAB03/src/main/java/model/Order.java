package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "order_status")
    private byte orderStatus;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "required_date")
    private LocalDate requiredDate;

    @Column(name = "shipped_date")
    private LocalDate shippedDate;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private List<OrderItem> orderItems;
}