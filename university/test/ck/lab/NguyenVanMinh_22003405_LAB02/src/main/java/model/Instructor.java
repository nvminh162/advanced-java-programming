package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor

@Entity
@DiscriminatorValue("Instructor")
public class Instructor extends Person {
    @Column(name = "HireDate")
    private LocalDateTime hireDate;

    @ManyToMany
    @JoinTable(
            name = "CourseInstructor",
            joinColumns = @JoinColumn(name = "PersonID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID")
    )
    @ToString.Exclude
    private Set<Course> courses = new LinkedHashSet<>();

    public Instructor(String firstName, String lastName, LocalDateTime hireDate) {
        super(firstName, lastName);
        this.hireDate = hireDate;
    }
}
