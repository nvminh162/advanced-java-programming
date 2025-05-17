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
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Product> products;
}
