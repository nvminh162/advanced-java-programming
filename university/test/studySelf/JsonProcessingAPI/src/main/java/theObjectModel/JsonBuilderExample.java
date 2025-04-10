package theObjectModel;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class JsonBuilderExample {
    public static void main(String[] args) {
        JsonArray ja = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("name", "John")
                        .add("age", 25)
                )
                .add(Json.createObjectBuilder()
                        .add("name", "Bob")
                        .add("age", 30)
                )
                .build();
        JsonObject jo = Json.createObjectBuilder()
                .add("people", ja)
                .build();
        System.out.println(jo);
    }
}
