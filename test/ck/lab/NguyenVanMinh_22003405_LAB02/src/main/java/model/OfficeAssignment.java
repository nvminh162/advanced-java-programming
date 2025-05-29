package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity
public class OfficeAssignment {
    @OneToOne
    @JoinColumn(name = "InstructorID")
    @Id
    private Instructor instructor;

    @Column(name = "Location")
    private String location;

    @Column(name = "Timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    public OfficeAssignment(Instructor instructor, String location, Timestamp timestamp) {
        this.instructor = instructor;
        this.location = location;
        this.timestamp = timestamp;
    }
}
