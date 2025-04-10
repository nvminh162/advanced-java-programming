package theObjectModel;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonWriter;

import java.io.StringWriter;

public class JsonWriterExample {
    public static void main(String[] args) {
        JsonObject jo = Json.createObjectBuilder()
                .add("product", "Jeans")
                .add("price", 50)
                .build();
        StringWriter sw = new StringWriter();
        JsonWriter jw = Json.createWriter(sw);
        jw.write(jo);
        jw.close();
        System.out.println(sw);
    }
}
