package model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Contact implements Serializable {
    private String phone;
    private String email;
}
