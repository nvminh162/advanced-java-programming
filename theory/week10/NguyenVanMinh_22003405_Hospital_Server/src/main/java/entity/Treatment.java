package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treatment implements Serializable {
    private Doctor doctor;
    private Patient patient;
    private LocalDate startDate;
    private LocalDate endDate;
    private String diagnosis;
}
