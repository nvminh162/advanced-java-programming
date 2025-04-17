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
@Table(name = "office_assignment")
public class OfficeAssignment implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "instructor_id", unique = true)
    private Instructor instructor;

    @Column(columnDefinition = "nvarchar(50)")
    private String location;
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp timestamp;
}
