package jsonobjectmodel.util;

import jakarta.json.*;
import model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    /*
    1. Convert from `Java Object` to `Json Object`
    Employee(22000001, "Thai Tuan 1", 1000000) => {"id": 22000001, "name": "Thai Tuan 1", "salary": 1000000}
    */
    public static JsonObject toJsonObject(Employee employee) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("id", employee.getId())
                .add("name", employee.getName())
                .add("salary", employee.getSalary());
        return jsonObjectBuilder.build();
    }

    /*
    2. Convert from `Java Object` to `Json Object` (write to file)
    Employee(22000002, "Thai Tuan 2", 2000000) => {"id": 22000002, "name": "Thai Tuan 2", "salary": 2000000}
    */
    public static void toJsonObjectFile(Employee employee, File file) {
        try (JsonWriter jsonWriter = Json.createWriter(new FileWriter(file))) {
            /*Call method 1*/
            JsonObject jsonObject = toJsonObject(employee);
            jsonWriter.writeObject(jsonObject);
            System.out.println("Written: " + jsonObject.toString() + " to : " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    3. Convert from `List Java Object` to `Json Array`
    List<Employee> => [{}, {}]
    */
    public static JsonArray toJsonArray(List<Employee> employees) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Employee employee : employees) {
            /*Call method 1*/
            JsonObject jsonObject = toJsonObject(employee);
            jsonArrayBuilder.add(jsonObject);
        }
        return jsonArrayBuilder.build();
    }

    /*
    4. Convert from `List Java Object` to `Json Array` (write to file)
    List<Employee> => [{}, {}]
    */
    public static void toJsonArrayFile(List<Employee> employees, File file) {
        try (JsonWriter jsonWriter = Json.createWriter(new FileWriter(file))) {
            /*Call method 3*/
            JsonArray jsonArray = toJsonArray(employees);
            jsonWriter.writeArray(jsonArray);
            System.out.println("Written: " + jsonArray.toString() + " to : " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    5. Convert from `Json Object` to `Java Object`
    {"id": 22000001, "name": "Thai Tuan 1", "salary": 1000000} => Employee(22000001, "Thai Tuan 1", 1000000)
    */
    public static Employee toObject(String jsonString) {
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonString))) {
            JsonObject jsonObject = jsonReader.readObject();
            long id = jsonObject.getJsonNumber("id").longValue();
            String name = jsonObject.getString("name");
            double salary = jsonObject.getJsonNumber("salary").doubleValue();
            return new Employee(id, name, salary);
        }
    }

    /*
    6. Convert from `Json Array` to `List Java Object`
    [{"id": 22000061, "name": "Thai Tuan 61", "salary": 6100000}, {"id": 22000062, "name": "Thai Tuan 62", "salary": 6200000}] => List<Employee>
    */
    public static List<Employee> toObjectList(String jsonString) {
        List<Employee> employees = new ArrayList<>();
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonString))) {
            JsonArray jsonArray = jsonReader.readArray();
            for (JsonValue jsonValue : jsonArray) {
                JsonObject jsonObject = (JsonObject) jsonValue;
                long id = jsonObject.getJsonNumber("id").longValue();
                String name = jsonObject.getString("name");
                double salary = jsonObject.getJsonNumber("salary").doubleValue();
                employees.add(new Employee(id, name, salary));
            }
        }
        return employees;
    }

    /*
    7. Read from File to `List Java Object`
    FILE [{}, {}] => List<Employee>
    */
    public static List<Employee> toObjectList(File file) {
        List<Employee> employees = new ArrayList<>();
        try (JsonReader jsonReader = Json.createReader(new FileReader(file))) {
            JsonArray jsonArray = jsonReader.readArray();
            for (JsonValue value : jsonArray) {
                JsonObject jsonObject = value.asJsonObject();
                long id = jsonObject.getJsonNumber("id").longValue();
                String name = jsonObject.getString("name");
                double salary = jsonObject.getJsonNumber("salary").doubleValue();
                employees.add(new Employee(id, name, salary));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    /*
    8. Read from File to `Java Object`
    FILE {} => Employee
    */
    public static Employee toObject(File file) {
        try (JsonReader jsonReader = Json.createReader(new FileReader(file))) {
            JsonObject jsonObject = jsonReader.readObject();
            long id = jsonObject.getJsonNumber("id").longValue();
            String name = jsonObject.getString("name");
            double salary = jsonObject.getJsonNumber("salary").doubleValue();
            return new Employee(id, name, salary);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}