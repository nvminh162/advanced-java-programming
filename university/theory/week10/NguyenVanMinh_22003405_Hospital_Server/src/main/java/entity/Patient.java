package entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Person implements Serializable {
    private Gender gender;
    private String dateOfBirth;
    private String address;

    public Patient(String id, String name, String phone, Gender gender, String dateOfBirth, String address) {
        super(id, name, phone);
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}
