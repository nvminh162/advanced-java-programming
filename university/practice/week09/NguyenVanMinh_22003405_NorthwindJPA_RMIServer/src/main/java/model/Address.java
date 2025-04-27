package model;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Address implements Serializable {
    private String street;
    private String city;
    private String state;

    @Column(name = "zip_code")
    private String zipCode;
}
