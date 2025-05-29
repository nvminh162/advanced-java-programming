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
@Table(name = "stocks")
@IdClass(Stock.StockId.class)
public class Stock implements Serializable {
    @ManyToOne
    @JoinColumn(name = "product_id")
    @Id
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @Id
    private Store store;

    private int quantity;

    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StockId implements Serializable {
        private Store store;
        private Product product;
    }
}
