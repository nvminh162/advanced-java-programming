package service;

import model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService extends GenericService<Student, Integer> {
    Map<Student, Double> getAverageScoreOfStudents();
    List<Student> listStudentsStudyingCourseWithHighestScore(String courseName);
}
