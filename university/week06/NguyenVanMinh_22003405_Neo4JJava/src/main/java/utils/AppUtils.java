package utils;

import models.Course;
import org.neo4j.driver.AuthToken;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;

import org.neo4j.driver.Driver;
import org.neo4j.driver.types.Node;

public class AppUtils {
    public static Driver getDriver() {
        String username = "neo4j";
        String password = "sapassword";
        AuthToken authToken = AuthTokens.basic(username, password);
        String uri = "neo4j://localhost:7687";
        return GraphDatabase.driver(uri, authToken);
    }

    public static Course toCourse(Node node) {
        Course course = new Course();
        course.setId(node.get("course_id").asString());
        course.setName(node.get("name").asString());
        course.setHours(node.get("hours").asInt());
        return course;
    }
}
