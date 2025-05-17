package model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "stocks")
@IdClass(Stock.stockId.class)
public class Stock {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @ToString.Exclude
    private Product product;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    @ToString.Exclude
    private Store store;

    private int quantity;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class stockId {
        private Product product;
        private Store store;
    }
}