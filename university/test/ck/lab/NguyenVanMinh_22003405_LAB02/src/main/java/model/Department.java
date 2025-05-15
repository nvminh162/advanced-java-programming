package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "DepartmentID")
    private int id;

    @Column(name = "Administrator")
    private String administrator;

    @Column(name = "Budget")
    private double budget;

    @Column(name = "Name")
    private String name;

    @Column(name = "StartDate")
    private LocalDateTime startDate;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private Set<Course> courses = new LinkedHashSet<>();

    public Department(String administrator, double budget, String name, LocalDateTime startDate) {
        this.administrator = administrator;
        this.budget = budget;
        this.name = name;
        this.startDate = startDate;
    }
}
