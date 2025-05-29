package model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "order_items")
@IdClass(OrderItem.orderItemId.class)
public class OrderItem {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @ToString.Exclude
    private Product product;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @ToString.Exclude
    private Order order;

    private int quantity;

    @Column(name = "list_price", columnDefinition = "DECIMAL(10,2)")
    private double listPrice;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private double discount;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class orderItemId {
        private Product product;
        private Order order;
    }
}
