package dao;

import model.Department;
import model.Student;
import model.StudentGrade;

import java.util.List;
import java.util.Map;

public interface StudentDAO extends GenericDAO<Student, Integer> {
    // Calculate the average score of the students' courses
    Map<Student, Double>getAverageScoreOfStudents();


    List<Student> listStudentsStudyingCourseWithHighestScore(String departmentName);
}
