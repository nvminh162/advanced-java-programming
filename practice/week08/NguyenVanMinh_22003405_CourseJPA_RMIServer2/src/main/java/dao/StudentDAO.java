package dao;

import model.Student;

import java.util.List;
import java.util.Map;

public interface StudentDAO extends GenericDAO<Student, Integer> {
    Map<Student, Double> getAverageScoreOfStudents();
    List<Student> listStudentsStudyingCourseWithHighestScore(String courseName);
}
