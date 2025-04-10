package theObjectModel;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.StringReader;

public class JsonReaderExample {
    public static void main(String[] args) {
        String jsonString = "{\"name\":\"Charlie\",\"age\":35}";

        JsonReader jr = Json.createReader(new StringReader(jsonString));
        JsonObject jo = jr.readObject();
        jr.close();

        System.out.println("\nName: " + jo.getString("name"));
        System.out.println("\nAge: " + jo.getInt("age"));
    }
}
