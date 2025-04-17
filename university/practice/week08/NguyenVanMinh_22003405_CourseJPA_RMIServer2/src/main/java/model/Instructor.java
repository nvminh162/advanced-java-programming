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
@DiscriminatorValue("instructor")
public class Instructor extends Person implements Serializable {
    @Column(name = "hire_date")
    private LocalDateTime hireDate;

    @ManyToMany
    @JoinTable(
            name = "course_instructor",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @ToString.Exclude
    private Set<Course> courses;
}
