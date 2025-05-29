package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Embeddable
public class Contact {
    @Column(columnDefinition = "VARCHAR(25)")
    private String phone;
    @Column(columnDefinition = "VARCHAR(255)")
    private String email;
}