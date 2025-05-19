package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "people")
public class Person implements Serializable {
    @Id
    @Column(name = "person_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    private String email;

    @Column(name = "professional_title")
    private String professionalTitle;

    @OneToMany(mappedBy = "person")
    @ToString.Exclude
    private Set<Reviews> reviews = new LinkedHashSet<>();
}