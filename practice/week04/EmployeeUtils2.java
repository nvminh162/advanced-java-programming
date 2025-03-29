package iuh.fit;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.List;

//The streaming API
public class EmployeeUtils2 {
// Employee emp = new Employee(100l, "Le Lan", 32.5);
//{"emp_id":100,"name":"Le Lan","salary":32.5}
    public static String toJson(Employee emp){
        StringWriter sw = null;
        try(JsonGenerator gen = Json.createGenerator(sw = new StringWriter())){
            gen
                    .writeStartObject()
                    .write("emp_id",emp.getId())
                    .write("name",emp.getName())
                    .write("salary",emp.getSalary())
                    .writeEnd();


        }
        return sw.toString();
    }
    public static void toJson(Employee emp, File file){
    }
    public static String toJson(List<Employee> emps){
        return null;
    }

// {"emp_id":100,"name":"Le Lan","salary":32.5}
    public static Employee fromJson(String jsonFile){//{}
        Employee emp = null;
        String keyName = "";
        try(JsonParser parser = Json.createParser(new FileReader(jsonFile))){
            while (parser.hasNext()){
                JsonParser.Event event = parser.next();
                switch (event){
                    case START_OBJECT -> emp = new Employee();
                    case KEY_NAME -> keyName = parser.getString();
                    case VALUE_NUMBER -> {
                        if("emp_id".equals(keyName))
                            emp.setId(parser.getLong());
                        else if("salary".equals(keyName))
                            emp.setSalary(parser.getBigDecimal().doubleValue());
                    }
                    case VALUE_STRING -> emp.setName(parser.getString());
                    case END_OBJECT -> {
                        return emp;
                    }
                    default -> {}
                }
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return emp;
    }
    public static List<Employee> fromJson(File jsonFile){//[]
        return null;
    }
}
