package run;

import daos.CourseDao;
import daos.DepartmentDAO;
import models.Course;
import utils.AppUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Course
        CourseDao courseDao = new CourseDao(AppUtils.getDriver(), "week06");
        Course course = new Course();
        course.setId("JAVA02");
        course.setName("Basic Java Programming");
        course.setHours(80);

        //#1. ADD
        /*boolean rs1 = courseDao.addCourse(course);
        System.out.println(rs1);*/

        //#2. UPDATE
        /*boolean rs2 = courseDao.updateCourse(course);
        System.out.println(rs2);*/

        //#3. Delete
        /*boolean rs3 = courseDao.deleteCourse("JAVA02");
        System.out.println(rs3);*/

        //#4. Find ID
        /*Course rs4 = courseDao.findById("JAVA02");
        System.out.println(rs4);*/

        //#5. List
        /*List<Course> courses = courseDao.listCourse(3, 3);
        courses.forEach(System.out::println);*/

        //#6. Delete All
        /*int rs6 = courseDao.deleteAllCourses();
        System.out.println(rs6);*/

        DepartmentDAO departmentDAO = new DepartmentDAO(AppUtils.getDriver(), "week06");
        departmentDAO.listNoStudentsByDepartment()
                .entrySet()
                .forEach(entry -> {
                    System.out.println("Department name: " + entry.getKey());
                    System.out.println("enrollment number: " + entry.getValue());
                });
    }
}
