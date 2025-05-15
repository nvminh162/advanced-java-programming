package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor

@Entity
public class OnsiteCourse extends Course {
    @Column(name = "Days")
    private String days;

    @Column(name = "Location")
    private String location;

    @Column(name = "Time")
    private LocalTime time;

    public OnsiteCourse(int credits, String title, String days, String location, LocalTime time) {
        super(credits, title);
        this.days = days;
        this.location = location;
        this.time = time;
    }
}
