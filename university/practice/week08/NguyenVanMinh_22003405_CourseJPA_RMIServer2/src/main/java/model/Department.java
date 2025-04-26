package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private int id;

    @Column(name="Administrator")
    private int administrator;

    @Column(name="Budget")
    private double budget;

    @Column(name="Name", columnDefinition = "nvarchar(50)")
    private String name;

    @Column(name = "StartDate")
    private LocalDateTime startDate;
}
