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
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int id;
    private int administrator;
    private double budget;
    @Column(columnDefinition = "nvarchar(50)")
    private String name;
    @Column(name = "start_date")
    private LocalDateTime startDate;
}
