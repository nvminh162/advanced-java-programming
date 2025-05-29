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
public class Address {
    @Column(columnDefinition = "VARCHAR(255)")
    private String street;

    @Column(columnDefinition = "VARCHAR(50)")
    private String city;

    @Column(columnDefinition = "VARCHAR(10)")
    private String state;

    @Column(name = "zip_code", columnDefinition = "VARCHAR(5)")
    private String zipCode;
}