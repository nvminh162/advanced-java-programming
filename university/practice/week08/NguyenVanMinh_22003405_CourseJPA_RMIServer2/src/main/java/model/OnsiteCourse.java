package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class OnsiteCourse extends Course implements Serializable {
    @Column(name = "Days", columnDefinition = "nvarchar(50)")
    private String days;

    @Column(name="Location", columnDefinition = "nvarchar(50)")
    private String location;

    @Column(name="Time")
    private LocalTime time;
}
