package iuh.fit.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
public class Student {
    private String id;
    @EqualsAndHashCode.Exclude
    private String name;
    @EqualsAndHashCode.Exclude
    private double gpa;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Course> courses;
}