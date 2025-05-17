package model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
@Getter
@Setter

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    protected int id;

    @Column(name = "first_name", nullable = false)
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @Embedded
    protected Contact contact;
}