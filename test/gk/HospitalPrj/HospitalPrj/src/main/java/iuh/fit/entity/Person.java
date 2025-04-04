package iuh.fit.entity;

import lombok.*;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SetDter
public class Person implements Serializable {
    protected String id;
    protected String name;
    protected String phone;
}
