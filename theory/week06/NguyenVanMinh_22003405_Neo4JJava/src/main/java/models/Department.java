package models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Department {
    private String id;
    @EqualsAndHashCode.Exclude
    private String name;
    @EqualsAndHashCode.Exclude
    private String dean;
    @EqualsAndHashCode.Exclude
    private String building;
    @EqualsAndHashCode.Exclude
    private String room;
}
