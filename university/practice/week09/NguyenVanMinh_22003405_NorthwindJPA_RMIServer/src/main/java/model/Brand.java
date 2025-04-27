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
@Table(name = "brands", uniqueConstraints = {
        @UniqueConstraint(name = "uc_brand_name", columnNames = "brand_name")
})
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private int id;

    @Column(name = "brand_name")
    private String name;

    @OneToMany(mappedBy = "brand")
    @ToString.Exclude
    private List<Product> products;

}
