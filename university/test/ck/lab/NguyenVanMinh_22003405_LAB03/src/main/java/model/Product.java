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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "product_name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "model_year", columnDefinition = "SMALLINT")
    private int modelYear;

    @Column(name = "list_price", columnDefinition = "DECIMAL(10, 2)")
    private double listPrice;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<Stock> stocks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}