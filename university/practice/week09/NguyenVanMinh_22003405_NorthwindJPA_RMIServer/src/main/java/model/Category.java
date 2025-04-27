package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "categories", uniqueConstraints = {
        @UniqueConstraint(name = "uc_category_name", columnNames = "category_name")
})
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Product> products;
}
