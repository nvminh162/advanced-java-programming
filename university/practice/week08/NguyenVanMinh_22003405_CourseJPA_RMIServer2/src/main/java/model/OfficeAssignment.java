package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class OfficeAssignment implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "InstructorID", unique = true)
    private Instructor instructor;

    @Column(name="Location", columnDefinition = "nvarchar(50)")
    private String location;
    @Column(name="Timestamp", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp timestamp;
}
