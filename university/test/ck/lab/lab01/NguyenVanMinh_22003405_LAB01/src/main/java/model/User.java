package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "user_id")
    private int id;

    @Column(columnDefinition = "VARCHAR(45)", nullable = false)
    private String username;

    @Column(columnDefinition = "VARCHAR(45)", nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(45)", nullable = false)
    private String email;

    @ManyToMany(mappedBy = "users")
    @ToString.Exclude
    private Set<Group> groups = new LinkedHashSet<>();
}
