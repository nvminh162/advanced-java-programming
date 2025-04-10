package theObjectModel;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;

import java.io.StringWriter;

public class JsonGeneratorExample {
    public static void main(String[] args) {
        StringWriter sw = new StringWriter();
        JsonGenerator jg = Json.createGenerator(sw);
        jg.writeStartObject()
                .write("name", "Bob")
                .write("age", 18)
                .writeEnd();
        jg.close();
        System.out.println(sw.toString());
    }
}
