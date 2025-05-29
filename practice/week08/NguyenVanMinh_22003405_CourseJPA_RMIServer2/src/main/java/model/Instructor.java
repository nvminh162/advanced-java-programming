package model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue("Instructor")
public class Instructor extends Person implements Serializable {
    @Column(name = "HireDate")
    private LocalDateTime hireDate;

    @ManyToMany
    @JoinTable(
            name = "CourseInstructor",
            joinColumns = @JoinColumn(name = "PersonID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID")
    )
    @ToString.Exclude
    private Set<Course> courses;
}
