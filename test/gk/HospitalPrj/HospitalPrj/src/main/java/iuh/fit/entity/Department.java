package iuh.fit.entity;

import lombok.*;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Department implements Serializable {
    private String id;
    private String name;
    private String location;
}
