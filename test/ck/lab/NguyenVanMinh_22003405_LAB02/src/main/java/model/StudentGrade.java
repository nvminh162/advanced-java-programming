package model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class StudentGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "EnrollmentID")
    private int id;

    @Column(name = "Grade")
    private double grade;

    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    public StudentGrade(Course course, Student student, double grade) {
        this.course = course;
        this.student = student;
        this.grade = grade;
    }
}
