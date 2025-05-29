package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id", nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "brand_name", columnDefinition = "VARCHAR(255)")
    private String name;

    @OneToMany(mappedBy = "brand")
    @ToString.Exclude
    private List<Product> products;
}