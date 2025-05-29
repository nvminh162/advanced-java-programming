package jsonstreamapi;

import jsonstreamapi.util.Util;
import model.Employee;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee(22002995, "Đỗ Thành Văn", 9000000);
        Employee employee2 = new Employee(220029952, "Đỗ Thành Văn 2", 90000002);
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee);

        String jsonObjectSample = "{\"id\":22003465,\"name\":\"Le Duan\",\"salary\":868855}";

        /*1.*/
        String jsonObject = Util.toJsonObject(employee);
        System.out.println(jsonObject);
        /*2.*/
        Util.toJsonObjectFile(employee, new File("src/main/java/jsonstreamapi/data/employee2.json"));
        /*3.*/
        String jsonArray = Util.toJsonArray(employees);
        System.out.println(jsonArray);
        /*4.*/
        Employee employee1 = Util.toObject(jsonObjectSample);
        System.out.println(employee1);
        /*5.*/
        List<Employee> employeesList = Util.toObjectList(new File("json/sample2.json"));
        System.out.println(employeesList);
    }
}
