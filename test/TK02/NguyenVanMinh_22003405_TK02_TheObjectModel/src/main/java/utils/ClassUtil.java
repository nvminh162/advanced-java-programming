package utils;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import model.Address;
import model.Classroom;
import model.Student;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClassUtil {
    public static List<Classroom> fromJson(String fileName) {
        List<Classroom> classrooms = new ArrayList<>();
        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonArray ja = reader.readArray();
            classrooms = ja
                    .stream()
                    .map(jv -> (JsonObject) jv)
                    .map(ClassUtil::fromJsonToClassroom)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classrooms;
    }

    private static Classroom fromJsonToClassroom(JsonObject joClassroom) {
        Classroom classroom = new Classroom();
        classroom.setName(joClassroom.getString("name"));
        classroom.setTeacher(joClassroom.getString("teacher"));
        classroom.setRoom(joClassroom.getInt("room"));
        classroom.setStart_time(joClassroom.getString("start_time"));
        classroom.setEnd_time(joClassroom.getString("end_time"));

        JsonArray jaStudents = joClassroom.getJsonArray("students");
        if (jaStudents != null) {
            List<Student> students = jaStudents
                    .stream()
                    .map(jv -> (JsonObject) jv)
                    .map(ClassUtil::fromJsonToStudent)
                    .toList();
            classroom.setStudents(students);
        }
        return classroom;
    }

    private static Student fromJsonToStudent(JsonObject joStudent) {
        Student student = new Student();
        student.setStudent_id(joStudent.getInt("student_id"));
        student.setName(joStudent.getString("name"));
        student.setAge(joStudent.getInt("age"));
        student.setGpa(joStudent.getJsonNumber("gpa").doubleValue());

        JsonObject joAddress = joStudent.getJsonObject("address");
        if (joAddress != null) {
            student.setAddress(fromJsonToAddress(joAddress));
        }
        return student;
    }

    private static Address fromJsonToAddress(JsonObject joAddress) {
        Address address = new Address();
        address.setStreet(joAddress.getString("street"));
        address.setCity(joAddress.getString("city"));
        address.setState(joAddress.getString("state"));
        address.setZip(joAddress.getInt("zip"));
        return address;
    }

    public static List<Student> getListStudents(String className) {
        List<Classroom> classrooms = fromJson("json/sample.json");

        Optional<Classroom> foundClass = classrooms
                .stream()
                .filter(classroom -> classroom.getName().equals(className))
                .findFirst();

        return foundClass
                .map(Classroom::getStudents)
                .orElse(new ArrayList<>());
    }
}
