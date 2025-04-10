package iuh.fit.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class Course {
    @SerializedName("course_id")
    private String id;

    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    private int hours;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Department department;
}
