package streamAPI;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonExample {
    public static void main(String[] args) {
        // Tạo JSON bằng JsonGenerator
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject()
                .write("name", "Alice")
                .write("age", 25)
                .write("city", "Paris")
                .writeEnd();
        generator.close();

        String jsonString = writer.toString();
        System.out.println("Generated JSON: " + jsonString);

        // Đọc JSON bằng JsonParser
        JsonParser parser = Json.createParser(new StringReader(jsonString));
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            System.out.println("Event: " + event);
        }
        parser.close();
    }
}
