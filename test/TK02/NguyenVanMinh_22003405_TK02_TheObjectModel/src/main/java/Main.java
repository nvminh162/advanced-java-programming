import model.Classroom;
import model.Student;
import utils.ClassUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Classroom> classrooms = ClassUtil.fromJson("json/sample.json");
        classrooms.forEach(System.out::println);

//        List<Student> students = ClassUtil.listStudents("Math");
//        if (students.isEmpty()) {
//            System.out.println("Không tìm thấy lớp học hoặc lớp không có sinh viên.");
//        } else {
//            students.forEach(s -> System.out.println(s.getName() + " - GPA: " + s.getGpa()));
//        }


    }
}
