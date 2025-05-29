package jsonobjectmodel;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jsonobjectmodel.util.Util;
import model.Employee;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        1. Convert from Java Object to Json
        Employee(22000001, "Thai Tuan 1", 1000000) => {"id": 22000001, "name": "Thai Tuan 1", "salary": 1000000}
        */
        Employee employee1 = new Employee(22000001, "Thai Tuan 1", 1000000);
        JsonObject jsonObject1 = Util.toJsonObject(employee1);
        System.out.println(jsonObject1);
        System.out.println("*************************************************************");
        /*
        2. Convert from Java Object to Json (write to file)
        Employee(22000002, "Thai Tuan 2", 2000000) => {"id": 22000002, "name": "Thai Tuan 2", "salary": 2000000}
        */
        Employee employee2 = new Employee(22000002, "Thai Tuan 2", 2000000);
        Util.toJsonObjectFile(employee2, new File("src/main/java/jsonobjectmodel/data/employee2.json"));
        System.out.println("*************************************************************");

        /*3 & 4 List employee*/
        List<Employee> employees34 = new ArrayList<>();
        employees34.add(employee1);
        employees34.add(employee2);
        JsonArray jsonArray34 = Util.toJsonArray(employees34);
        /*
        3. Convert from List Java Object to Json Array
        List<Employee> => [{}, {}]
        */
        System.out.println(jsonArray34);
        System.out.println("*************************************************************");
        /*
        4. Convert from List Java Object to Json Array (write to file)
        List<Employee> => [{}, {}]
        */
        Util.toJsonArrayFile(employees34, new File("src/main/java/jsonobjectmodel/data/employee4.json"));
        System.out.println("*************************************************************");
        /*
        5. Convert from Json Object to Java Object
        {"id": 22000005, "name": "Thai Tuan 5", "salary": 5000000} => Employee(22000005, "Thai Tuan 5", 5000000)
        */
        String jsonObjectString5 = "{\"id\": 22000005, \"name\": \"Thai Tuan 5\", \"salary\": 5000000}";
        Employee employee5 = Util.toObject(jsonObjectString5);
        System.out.println(employee5);
        System.out.println("*************************************************************");
        /*
        6. Convert from `Json Array` to `List Java Object`
        [{"id": 22000061, "name": "Thai Tuan 61", "salary": 6100000}, {"id": 22000062, "name": "Thai Tuan 62", "salary": 6200000}] => List<Employee>
        */
        String jsonArrayString6 = "[{\"id\": 22000061, \"name\": \"Thai Tuan 61\", \"salary\": 6100000}, {\"id\": 22000062, \"name\": \"Thai Tuan 62\", \"salary\": 6200000}]";
        List<Employee> employees6 = Util.toObjectList(jsonArrayString6);
        System.out.println(employees6);
        System.out.println("*************************************************************");
        /*
        7. Read from File to `List Java Object`
        FILE [{}, {}] => List<Employee>
        */
        List<Employee> employees7 = Util.toObjectList(new File("json/sample2.json"));
        System.out.println(employees7);
        System.out.println("*************************************************************");
        /*
        8. Read from File to `Java Object`
        FILE {} => Employee
        */
        Employee employee8 = Util.toObject(new File("json/sample1.json"));
        System.out.println(employee8);
        System.out.println("*************************************************************");
    }
}
