package jsonstreamapi.util;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonWriter;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static String toJsonObject(Employee employee) {
        StringWriter stringWriter = new StringWriter();
        try (JsonGenerator jsonGenerator = Json.createGenerator(stringWriter)) {
            jsonGenerator
                    .writeStartObject()
                    .write("id", employee.getId())
                    .write("name", employee.getName())
                    .write("salary", employee.getSalary())
                    .writeEnd();
        }
        return stringWriter.toString();
    }

    public static void toJsonObjectFile(Employee emp, File file) {
        try (var writer = new FileWriter(file)) {
            writer.write(toJsonObject(emp));
            System.out.println("File written to " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonArray(List<Employee> employees) {
        StringWriter stringWriter = new StringWriter();
        try (JsonGenerator jsonGenerator = Json.createGenerator(stringWriter)) {
            jsonGenerator.writeStartArray();
            for (Employee employee : employees) {
                jsonGenerator.writeStartObject()
                        .write("id", employee.getId())
                        .write("name", employee.getName())
                        .write("salary", employee.getSalary())
                        .writeEnd();
            }
            jsonGenerator.writeEnd();
        }
        return stringWriter.toString();
    }

    public static Employee toObject(String jsonObjectStr) {
        Employee employee = null;
        String keyName = "";
        try (JsonParser jsonParser = Json.createParser(new StringReader(jsonObjectStr))) {
            while (jsonParser.hasNext()) {
                JsonParser.Event event = jsonParser.next();
                switch (event) {
                    case START_OBJECT -> employee = new Employee();
                    case KEY_NAME -> keyName = jsonParser.getString();
                    case VALUE_NUMBER -> {
                        if ("id".equals(keyName)) {
                            employee.setId(jsonParser.getInt());
                        } else if ("salary".equals(keyName)) {
                            employee.setSalary(jsonParser.getBigDecimal().doubleValue());
                        }
                    }
                    case VALUE_STRING -> employee.setName(jsonParser.getString());
                    case END_OBJECT -> {
                        return employee;
                    }
                    default -> {
                    }
                }
            }
        }
        return employee;
    }

    //[]
    public static List<Employee> toObjectList(File jsonFile) {
        List<Employee> employees = new ArrayList<>();
        Employee employee = null;
        String keyName = "";

        try (JsonParser jsonParser = Json.createParser(new FileReader(jsonFile))) {
            while (jsonParser.hasNext()) {
                JsonParser.Event event = jsonParser.next();
                switch (event) {
                    case START_OBJECT -> employee = new Employee();
                    case KEY_NAME -> keyName = jsonParser.getString();
                    case VALUE_STRING -> {
                        if ("name".equals(keyName)) {
                            employee.setName(jsonParser.getString());
                        }
                    }
                    case VALUE_NUMBER -> {
                        if ("id".equals(keyName)) {
                            employee.setId(jsonParser.getInt());
                        } else if ("salary".equals(keyName)) {
                            employee.setSalary(jsonParser.getBigDecimal().doubleValue());
                        }
                    }
                    case END_OBJECT -> employees.add(employee);
                    default -> {}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

}
