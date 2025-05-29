import model.Classroom;
import model.Student;
import utils.ClassUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        testAndWriteResults("export/NguyenVanMinh_22003405.txt");
    }

    public static void testAndWriteResults(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Test 1: Reading all classrooms
            writer.println("Test 1: Reading all classrooms from JSON");
            List<Classroom> classrooms = ClassUtil.fromJson("json/sample.json");
            writer.println("Found " + classrooms.size() + " classrooms");
            classrooms.forEach(classroom -> writer.println(classroom));
            writer.println();

            // Test 2: Finding students in Math class
            writer.println("Test 2: Finding students in Math class");
            List<Student> mathStudents = ClassUtil.getListStudents("Math");
            if (mathStudents.isEmpty()) {
                writer.println("No students found in Math class");
            } else {
                writer.println("Found " + mathStudents.size() + " students in Math class:");
                mathStudents.forEach(student ->
                        writer.println("- " + student.getName() + " (ID: " + student.getStudent_id() +
                                ", GPA: " + student.getGpa() + ")"));
            }
            writer.println();

            // Test 3: Finding students in non-existent class
            writer.println("Test 3: Finding students in Physics class (non-existent)");
            List<Student> physicsStudents = ClassUtil.getListStudents("Physics");
            if (physicsStudents.isEmpty()) {
                writer.println("No students found in Physics class");
            } else {
                writer.println("Found " + physicsStudents.size() + " students in Physics class");
            }

            System.out.println("Test results written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}