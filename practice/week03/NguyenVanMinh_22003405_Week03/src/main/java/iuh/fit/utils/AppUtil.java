package iuh.fit.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import iuh.fit.models.Course;
import org.neo4j.driver.AuthToken;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

import java.util.Map;

public class AppUtil {
    private static final Gson GSON = new Gson();

    public static Driver getDriver() {
        String username = "neo4j";
        String password = "sapassword";
        AuthToken authToken = AuthTokens.basic(username, password);
        String uri = "neo4j://localhost:7687";
        return GraphDatabase.driver(uri, authToken);
    }


    public static Course toCourse(Node node) {
        Course course = new Course();
        String id = node.get("course_id").asString();
        String name = node.get("name").asString();
        int hours = node.get("hours").asInt();

        course.setId(id);
        course.setName(name);
        course.setHours(hours);

        return course;
    }

    public static <T> T toObject(Node node, Class<T> cls) {
        Map<String, Object> map = node.asMap();
        String json = GSON.toJson(map);
        return GSON.fromJson(json, cls);
    }

    public static <T> Map<String, Object> asMap(T cls){
        String json = GSON.toJson(cls);
        return GSON.fromJson(json,  new TypeToken<Map<String, Object>>(){}  );
    }
}
