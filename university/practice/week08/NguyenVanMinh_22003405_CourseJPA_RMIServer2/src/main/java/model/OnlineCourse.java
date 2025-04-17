package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "online_course")
public class OnlineCourse extends Course implements Serializable {
    @Column(columnDefinition = "nvarchar(100)")
    private String url;
}
