package com.nvminh162.Runner;

import com.nvminh162.daos.CourseDao;
import com.nvminh162.models.Course;
import com.nvminh162.utils.AppUtil;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        CourseDao courseDao = new CourseDao(AppUtil.getDriver(), "nguyenvanminh22003405");
        Course course = new Course();
        course.setId("java01");
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
        /*Course rs4 = courseDao.findById("java01");
        System.out.println(rs4);*/

        //#5. List
        /*List<Course> courses = courseDao.listCourse(3, 3);
        courses.forEach(System.out::println);*/
    }
}
