package iuh.fit.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Treatment implements Serializable {
    private Doctor doctor;
    private Patient patient;
    private LocalDate startDate;
    private LocalDate endDate;
    private String diagnosis;
}
