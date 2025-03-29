package theObjectModel;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class JsonValueExample {
    public static void main(String[] args) {
        JsonObject jo = Json.createObjectBuilder()
                .add("name", "John")
                .add("age", 25)
                .add("isMarried", false)
                .build();

        String name = jo.getString("name");
        int age = jo.getInt("age");
        boolean isMarried = jo.getBoolean("isMarried");

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Is Married: " + isMarried);
    }
}
