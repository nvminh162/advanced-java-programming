package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@DiscriminatorValue("student")
public class Student extends Person {
    private LocalDateTime enrollmentDate;

    public Student(String firstName, String lastName, LocalDateTime enrollmentDate) {
        super(firstName, lastName);
        this.enrollmentDate = enrollmentDate;
    }
}
