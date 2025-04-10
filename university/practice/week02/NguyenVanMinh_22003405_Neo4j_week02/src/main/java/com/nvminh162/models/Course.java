package com.nvminh162.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class Course {
    private String id;
    @EqualsAndHashCode.Exclude
    private String name;
    @EqualsAndHashCode.Exclude
    private int hours;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Department department;
}
