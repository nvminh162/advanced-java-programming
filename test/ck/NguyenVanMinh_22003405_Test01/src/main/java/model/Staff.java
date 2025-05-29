package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "staffs")
public class Staff {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "staff_id", columnDefinition = "BIGINT")
    private long id;

    @Column(name = "staff_name", columnDefinition = "NVARCHAR(100)")
    private String name;

    private int age;

    @Column(name = "refers", columnDefinition = "VARCHAR(255)")
    private String references;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(
            name = "phones",
            joinColumns = @JoinColumn(name = "staff_id", columnDefinition = "BIGINT"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"staff_id", "number"})
    )
    @Column(name = "number")
    private Set<String> phoneNumbers = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToOne(mappedBy = "staff")
    @ToString.Exclude
    private Profile profile;

    @ManyToMany(mappedBy = "staffs")
    @ToString.Exclude
    private Set<Project> projects = new LinkedHashSet<>();
}
