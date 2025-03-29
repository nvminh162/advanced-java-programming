package main;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import models.Employee;
import utils.EmployeeUtils;
import utils.EmployeeUtils2;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        Employee emp1 = new Employee(100, "Do Van", 32.5);
        Employee emp2 = new Employee(101, "Nguyen Minh", 45.0);

        //Employee thành Object Model API
        JsonObject empJson = EmployeeUtils.toJson(emp1);
        System.out.println("Employee to JSON (Object Model API): " + empJson);

        //Employee thành JSON sử dụng Streaming API
        String empJsonStream = EmployeeUtils2.toJson(emp1);
        System.out.println("Employee to JSON (Streaming API): " + empJsonStream);

        // Lưu JSON vào tệp sử dụng Object Model API
        try (FileWriter fileWriter = new FileWriter("src/main/java/data/employee.json")) {
            fileWriter.write(empJson.toString());
            System.out.println("JSON has been written to 'employee.json'.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Đọc JSON từ tệp sử dụng Object Model API
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("src/main/java/data/employee.json")));
            Employee empFromFile = EmployeeUtils.fromJson(jsonContent);
            System.out.println("Employee from JSON file (Object Model API): " + empFromFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Chuyển đổi danh sách Employee thành JSON sử dụng Object Model API
        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        JsonArray empArrayJson = EmployeeUtils.toJson(employees);
        System.out.println("Employee list to JSON (Object Model API): " + empArrayJson);

        // Chuyển đổi danh sách Employee thành JSON sử dụng Streaming API
         String empListJsonStream = EmployeeUtils2.toJson(employees);
         System.out.println("Employee list to JSON (Streaming API): " + empListJsonStream);
         
         //Chuyển đổi JSON về đối tượng Employee sử dụng Streaming API
        String jsonInput = "{\"emp_id\":100,\"name\":\"Le Lan\",\"salary\":32.5}";
        Employee empFromJsonStream = EmployeeUtils2.fromJson(jsonInput);
        System.out.println("Employee from JSON (Streaming API): " + empFromJsonStream);

        // Chuyển đổi JSON về đối tượng Employee sử dụng Object Model API
        Employee empFromJson = EmployeeUtils.fromJson2("{\"emp_id\":101,\"name\":\"Nguyen Minh\",\"salary\":45.0}");
        System.out.println("Employee from JSON (Object Model API): " + empFromJson);
    }
}
