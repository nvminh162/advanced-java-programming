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
public class OnlineCourse extends Course implements Serializable {
    @Column(name = "URL", columnDefinition = "nvarchar(100)")
    private String url;
}
