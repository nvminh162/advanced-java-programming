package theObjectModel;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class JsonExample {
    public static void main(String[] args) {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("name", "Alice")
                .add("age", 25)
                .add("city", "Paris")
                .build();
        System.out.println(jsonObject); // {"name":"Alice","age":25,"city":"Paris"}
    }
}

