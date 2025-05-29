package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "project_id", columnDefinition = "VARCHAR(50)")
    private String id;

    @Column(name = "project_name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(columnDefinition = "FLOAT")
    private double budget;

    @ManyToMany
    @JoinTable(
            name = "staff_projects",
            joinColumns = @JoinColumn(name = "project_id", columnDefinition = "VARCHAR(50)"),
            inverseJoinColumns = @JoinColumn(name = "staff_id", columnDefinition = "BIGINT")
    )
    @ToString.Exclude
    private Set<Staff> staffs = new LinkedHashSet<>();
}
