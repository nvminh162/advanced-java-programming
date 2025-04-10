package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int student_id;
    private String name;
    private int age;
    private double gpa;
    private Address address;
}
