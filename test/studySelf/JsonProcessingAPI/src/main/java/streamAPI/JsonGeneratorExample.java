package streamAPI;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import java.io.StringWriter;

public class JsonGeneratorExample {
    public static void main(String[] args) {
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);

        generator.writeStartObject()
                .write("name", "Alice")
                .write("age", 25)
                .write("city", "Paris")
                .writeEnd();
        generator.close();

        System.out.println("Generated JSON: " + writer.toString());
    }
}

