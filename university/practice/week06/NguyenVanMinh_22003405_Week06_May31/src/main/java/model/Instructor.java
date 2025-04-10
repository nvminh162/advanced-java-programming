package model;

import jakarta.persistence.*;
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
@DiscriminatorValue("Instructor")
@Table(name = "instructor")
public class Instructor extends Person {

    private LocalDateTime hireDate;

    public Instructor(String firstName, String lastName, LocalDateTime hireDate) {
        super(firstName, lastName);
        this.hireDate = hireDate;
    }
}