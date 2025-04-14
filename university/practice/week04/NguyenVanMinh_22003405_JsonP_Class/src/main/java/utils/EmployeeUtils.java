package utils;

import jakarta.json.*;
import models.Employee;

import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.List;

//The Java Object Model API
public class EmployeeUtils {
    //    Employee emp = new Employee(100l, "Le Lan", 32.5);
//    {"emp_id":100, "name":"Le Lan","salary":32.5}
    public static JsonObject toJson(Employee emp) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder
                .add("emp_id", emp.getId())
                .add("name", emp.getName())
                .add("salary", emp.getSalary());
        return builder.build();
    }

    public static void toJson(Employee emp, File file) {
        try (JsonWriter writer = Json.createWriter(new FileWriter(file))) {
            JsonObjectBuilder builder = Json.createObjectBuilder();
            builder
                    .add("emp_id", emp.getId())
                    .add("name", emp.getName())
                    .add("salary", emp.getSalary());

            JsonObject jo = builder.build();
            writer.writeObject(jo);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*public static JsonArray toJson(List<Employee> emps){
        return null;
    }*/
    public static JsonArray toJson(List<Employee> employees) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Employee emp : employees) {
            JsonObject empJson = Json.createObjectBuilder()
                    .add("emp_id", emp.getId())
                    .add("name", emp.getName())
                    .add("salary", emp.getSalary())
                    .build();
            arrayBuilder.add(empJson);
        }
        return arrayBuilder.build();
    }

    //    {"emp_id":100,"name":"Le Lan","salary":32.5}
    public static Employee fromJson(String json) {//{}
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            JsonObject jo = reader.readObject();
            long id = jo.getJsonNumber("emp_id").longValue();
            String name = jo.getString("name");
            double salary = jo.getJsonNumber("salary").doubleValue();
            return new Employee(id, name, salary);
        }
    }

    public static Employee fromJson2(String json) {
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            JsonObject jo = reader.readObject();
            long id = jo.getJsonNumber("emp_id").longValue();
            String name = jo.getString("name");
            double salary = jo.getJsonNumber("salary").doubleValue();

            return new Employee(id, name, salary);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<Employee> fromJson(File jsonFile) {//[]
        return null;
    }
}
