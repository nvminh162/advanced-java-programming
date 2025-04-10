package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {
    private String name;
    private String teacher;
    private int room;
    private String start_time;
    private String end_time;
    private List<Student> students;
}
