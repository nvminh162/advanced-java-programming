/*
package main;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class main2 {
    public static void main(String[] args) {
        String jsonString = "{\n" +
                "\"firstName\": \"John\",\n" +
                "\"lastName\": \"Smith\",\n" +
                "\"age\": 25,\n" +
                "\"address\": {\n" +
                "\"streetAddress\": \"21 2nd Street\",\n" +
                "\"city\": \"New York\",\n" +
                "\"state\": \"NY\",\n" +
                "\"postalCode\": 10021\n" +
                "},\n" +
                "\"phoneNumbers\": [\n" +
                "{\n" +
                "\"type\": \"home\",\n" +
                "\"number\": \"212 555-1234\"\n" +
                "},\n" +
                "{\n" +
                "\"type\": \"fax\",\n" +
                "\"number\": \"646 555-4567\"\n" +
                "}\n" +
                "]\n" +
                "}";

        JsonParser parser = Json.createParser(new StringReader(jsonString));

        // Các đối tượng giúp lưu trữ dữ liệu
        Person person = new Person();
        Address address = null;
        PhoneNumber phoneNumber = null;

        // Sử dụng Stack để theo dõi các đối tượng lồng nhau
        Stack<Object> stack = new Stack<>();
        String currentKey = null;

        while (parser.hasNext()) {
            Event event = parser.next();

            switch (event) {
                case START_OBJECT:
                    if (currentKey == null) {
                        // Bắt đầu đối tượng Person
                        stack.push(person);
                    } else if (currentKey.equals("address")) {
                        // Bắt đầu đối tượng Address
                        address = new Address();
                        stack.push(address);
                    } else if (currentKey.equals("phoneNumbers")) {
                        // Bắt đầu mảng phoneNumbers
                        stack.push(new ArrayList<PhoneNumber>());
                    }
                    break;

                case END_OBJECT:
                    if (currentKey != null && currentKey.equals("address")) {
                        // Kết thúc đối tượng Address
                        person.setAddress(address);
                        stack.pop();
                    } else if (currentKey != null && currentKey.equals("phoneNumbers")) {
                        // Kết thúc mảng phoneNumbers
                        person.setPhoneNumbers((List<PhoneNumber>) stack.pop());
                    } else if (stack.size() == 1) {
                        // Kết thúc đối tượng Person
                        stack.pop();
                    }
                    break;

                case KEY_NAME:
                    currentKey = parser.getString();
                    break;

                case VALUE_STRING:
                    if (currentKey != null) {
                        switch (currentKey) {
                            case "firstName":
                                person.setFirstName(parser.getString());
                                break;
                            case "lastName":
                                person.setLastName(parser.getString());
                                break;
                            case "streetAddress":
                                address.setStreetAddress(parser.getString());
                                break;
                            case "city":
                                address.setCity(parser.getString());
                                break;
                            case "state":
                                address.setState(parser.getString());
                                break;
                            case "type":
                                // Tạo đối tượng PhoneNumber cho mỗi phần tử trong mảng
                                phoneNumber = new PhoneNumber();
                                phoneNumber.setType(parser.getString());
                                break;
                            case "number":
                                phoneNumber.setNumber(parser.getString());
                                // Thêm phoneNumber vào mảng khi đã đọc xong một đối tượng phoneNumbers
                                ((List<PhoneNumber>) stack.peek()).add(phoneNumber);
                                break;
                        }
                    }
                    break;

                case VALUE_NUMBER:
                    if (currentKey != null) {
                        if (currentKey.equals("age")) {
                            person.setAge(parser.getInt());
                        } else if (currentKey.equals("postalCode")) {
                            address.setPostalCode(parser.getInt());
                        }
                    }
                    break;

                default:
                    break;
            }
        }

        System.out.println(person);
    }
}
*/
