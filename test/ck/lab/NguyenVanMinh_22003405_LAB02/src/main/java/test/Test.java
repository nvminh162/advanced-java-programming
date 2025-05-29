package test;

import dao.impl.DepartmentDAOImpl;
import dao.impl.StudentDAOImpl;
import model.Department;
import model.Student;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        /*Map<Department, Long> getNumberOfStudentsByDepartment = new DepartmentDAOImpl(Department.class).getNumberOfStudentsByDepartment();
        System.out.println("DepartmentName | NumberOfStudents");
        getNumberOfStudentsByDepartment.forEach((k, v) -> System.out.println(k.getName() + " : " + v));*/

        /*Map<Student, Double> getAverageScoreOfStudents = new StudentDAOImpl(Student.class).getAverageScoreOfStudents();
        System.out.println("StudentName | AVG_Score");
        getAverageScoreOfStudents.forEach((k, v) -> System.out.println(k.getFirstName() + ": " + v));*/

        /*List<Department> listDepartmentsWithoutStudents = new DepartmentDAOImpl(Department.class).listDepartmentsWithoutStudents();
        listDepartmentsWithoutStudents.forEach(System.out::println);*/

        List<Student> listStudentsStudyingCourseWithHighestScore = new StudentDAOImpl(Student.class).listStudentsStudyingCourseWithHighestScore("English");
        listStudentsStudyingCourseWithHighestScore.forEach(System.out::println);
    }
}
