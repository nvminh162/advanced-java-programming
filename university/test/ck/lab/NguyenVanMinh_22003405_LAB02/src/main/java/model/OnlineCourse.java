package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor

@Entity
public class OnlineCourse extends Course {
    @Column(name = "URL")
    private String url;

    public OnlineCourse(int credits, String title, String url) {
        super(credits, title);
        this.url = url;
    }
}
