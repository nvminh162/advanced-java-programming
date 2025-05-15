package model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor

@Entity
@DiscriminatorValue("Student")
public class Student extends Person {
    @Column(name = "EnrollmentDate")
    private LocalDateTime enrollmentDate;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private Set<StudentGrade> studentGrades = new LinkedHashSet<>();

    public Student(String firstName, String lastName, LocalDateTime enrollmentDate) {
        super(firstName, lastName);
        this.enrollmentDate = enrollmentDate;
    }
}
