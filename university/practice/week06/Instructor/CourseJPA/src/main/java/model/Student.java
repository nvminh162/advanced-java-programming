package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor

@Entity
@DiscriminatorValue("Student")
public class Student extends Person implements Serializable {
    private LocalDateTime enrollmentDate;

    public Student(String firstName, String lastName, LocalDateTime enrollmentDate) {
        super(firstName, lastName);
        this.enrollmentDate = enrollmentDate;
    }
}