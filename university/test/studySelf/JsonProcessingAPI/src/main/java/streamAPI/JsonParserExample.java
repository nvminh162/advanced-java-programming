package streamAPI;

import jakarta.json.stream.JsonParser;
import jakarta.json.Json;
import java.io.StringReader;

public class JsonParserExample {
    public static void main(String[] args) {
        String jsonString = "{\"name\":\"Alice\",\"age\":25,\"city\":\"Paris\"}";

        JsonParser parser = Json.createParser(new StringReader(jsonString));
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    System.out.print("Key: " + parser.getString() + " - ");
                    break;
                case VALUE_STRING:
                    System.out.println("Value (String): " + parser.getString());
                    break;
                case VALUE_NUMBER:
                    System.out.println("Value (Number): " + parser.getInt());
                    break;
                default:
                    break;
            }
        }
        parser.close();
    }
}

