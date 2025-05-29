package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "order_items")
@IdClass(OrderItem.OrderItemId.class)
public class OrderItem implements Serializable {
    @ManyToOne
    @JoinColumn(name = "order_id")
    @Id
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Id
    private Product product;

    private int quantity;

    @Column(name = "list_price")
    private double listPrice;

    private double discount;

    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemId implements Serializable {
        private Order order;
        private Product product;
    }
}
