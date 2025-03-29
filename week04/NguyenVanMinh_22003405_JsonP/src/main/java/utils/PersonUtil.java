package utils;

import entities.Address;
import entities.Person;
import entities.PhoneNumber;
import jakarta.json.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PersonUtil {
    public static List<Person> fromJson(String fileName) {
        List<Person> people = new ArrayList<>();
        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonArray ja = reader.readArray();
            people = ja
                    .stream()
                    .map(jv -> (JsonObject) jv)           //convert: JsonValue -> JsonObject
                    .map(jo -> fromJson(jo))     //convert: JsonObject -> JavaObject
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return people;
    }

    private static Person fromJson(JsonObject joPerson) {
        JsonObjectBuilder joBuilder = Json.createObjectBuilder();
        Person p = new Person();
        p.setFirstName(joPerson.getString("firstName"));
        p.setLastName(joPerson.getString("lastName"));
        p.setAge(joPerson.getInt("age"));

        JsonObject joAddress = joPerson.getJsonObject("address");
        if(joAddress != null) {
            Address a = new Address();
            a.setStreetAddress(joAddress.getString("streetAddress"));
            a.setCity(joAddress.getString("city"));
            a.setState(joAddress.getString("state"));
            a.setPostalCode(joAddress.getInt("postalCose"));
            p.setAddress(a);
        }

        JsonArray joPhones = joPerson.getJsonArray("phoneNumbers");
        if(joPhones != null) {
            List<PhoneNumber> phones = joPhones
                    .stream()
                    .map(jv -> (JsonObject)jv)
                    .map(joPhone -> {
                        PhoneNumber pn = new PhoneNumber();
                        pn.setType(joPhone.getString("type"));
                        pn.setNumber(joPhone.getString("number"));
                        return pn;
                    })
                    .toList();
            p.setPhoneNumbers(phones);
        }
        return p;
    }
}
