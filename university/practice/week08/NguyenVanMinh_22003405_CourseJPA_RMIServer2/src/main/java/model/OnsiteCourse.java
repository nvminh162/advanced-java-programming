package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "onsite_course")
public class OnsiteCourse extends Course implements Serializable {
    @Column(columnDefinition = "nvarchar(50)")
    private String days;
    @Column(columnDefinition = "nvarchar(50)")
    private String location;
    private LocalDateTime time;
}
