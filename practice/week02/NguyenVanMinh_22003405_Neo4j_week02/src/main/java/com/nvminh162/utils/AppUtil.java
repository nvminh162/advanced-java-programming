package com.nvminh162.utils;

import com.nvminh162.models.Course;
import org.neo4j.driver.AuthToken;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

public class AppUtil {
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
}
