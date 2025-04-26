package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID")
    protected int id;

    @Column(name = "Credits")
    protected int credits;

    @Column(name = "Title", columnDefinition = "nvarchar(100)")
    protected String title;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private Department department;

    @ManyToMany(mappedBy = "courses")
    @ToString.Exclude
    protected Set<Instructor> instructors;
}
