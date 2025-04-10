package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@DiscriminatorValue("Student")
@Table(name = "Student")
public class Student extends Person{

    private LocalDateTime enrollmentDate;

    public Student(String firstName, String lastName, LocalDateTime enrollmentDate) {
        super(firstName, lastName);
        this.enrollmentDate = enrollmentDate;
    }
}