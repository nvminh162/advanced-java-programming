package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "CourseID")
    protected int id;

    @Column(name = "Credits")
    protected int credits;

    @Column(name = "Title")
    protected String title;

    @ManyToMany(mappedBy = "courses")
    @ToString.Exclude
    protected Set<Instructor> instructors  = new LinkedHashSet<>();

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    protected Set<StudentGrade> studentGrade = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    protected Department department;

    public Course(int credits, String title) {
        this.credits = credits;
        this.title = title;
    }
}
