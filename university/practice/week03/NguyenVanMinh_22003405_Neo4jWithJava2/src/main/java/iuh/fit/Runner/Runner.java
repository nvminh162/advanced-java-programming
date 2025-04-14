package iuh.fit.Runner;

import iuh.fit.daos.CourseDao;
import iuh.fit.models.Course;
import iuh.fit.utils.AppUtil;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        CourseDao courseDao = new CourseDao(AppUtil.getDriver(), "nguyenvanminh22003405");

        Course course = new Course();
        course.setId("java02");
        course.setName("Basic Java Programming");
        course.setHours(50);

        //#1. ADD
        /*boolean rs1 = courseDao.addCourse(course);
        System.out.println(rs1);*/

        //#1. UPDATE
        /*boolean rs2 = courseDao.updateCourse(course);
        System.out.println(rs2);*/

        //#3. DELETE
        /*boolean rs3 = courseDao.deleteCourse("java01");
        System.out.println(rs3);*/

        //#4. GET BY ID
        Course rs4 = courseDao.findById("java02");
        System.out.println(rs4);

        //#5. List
        /*List<Course> courses = courseDao.listCourse(3, 3);
        courses.forEach(System.out::println);*/
    }
}
