package model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue("Student")
public class Student extends Person implements Serializable {
    @Column(name = "EnrollmentDate")
    private LocalDateTime enrollmentDate;
}
