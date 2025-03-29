package com.nvminh162.daos;

import com.nvminh162.models.Course;
import com.nvminh162.utils.AppUtil;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;

import java.util.List;
import java.util.Map;

public class CourseDao {
    private final Driver driver;
    private final SessionConfig sessionConfig;

    public CourseDao(Driver driver, String dbName) {
        this.driver = driver;
        this.sessionConfig = SessionConfig
                .builder()
                .withDatabase(dbName)
                .build();
    }

    public boolean addCourse(Course course) {
        String query = "CREATE (c:Course {course_id: $id, name: $name, hours: $hours})";
        Map<String, Object> params = Map.of(
                "id", course.getId(),
                "name", course.getName(),
                "hours", course.getHours()
        );
        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                ResultSummary resultSummary = tx.run(query, params).consume();
                return resultSummary.counters().nodesCreated() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCourse(Course course) {
        String query =
                """
                            MATCH (c:Course {course_id: $id})
                            SET c.name = $name, c.hours = $hours
                            RETURN count(c) AS updatedCount
                        """;
        Map<String, Object> params = Map.of(
                "id", course.getId(),
                "name", course.getName(),
                "hours", course.getHours()
        );
        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                var result = tx.run(query, params);
                return result.single().get("updatedCount").asInt() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCourse(String id) {
        String query = """
                 MATCH (c:Course {course_id: $id})
                 DETACH DELETE c
                """;
        Map<String, Object> params = Map.of("id", id);
        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                ResultSummary resultSummary = tx.run(query, params).consume();
                return resultSummary.counters().nodesDeleted() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Course findById(String id) {
        String query = "MATCH (c:Course {course_id:$id}) RETURN c";
        Map<String, Object> param = Map.of("id", id);
        try (var session = driver.session(sessionConfig)) {
            return session.executeRead(tx -> {
                Result result = tx.run(query, param);
                if (!result.hasNext())
                    return null;
                Node node = result.single().get("c").asNode();
                Course course = AppUtil.toCourse(node);
                return course;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Course> listCourse(int skip, int limit) {
        String query = "MATCH (c:Course) SKIP $skip LIMIT $limit RETURN c";

        Map<String, Object> params = Map.of(
                "skip", skip,
                "limit", limit
        );

        try (var session = driver.session(sessionConfig)) {
            return session.executeRead(tx -> {
                return tx.run(query, params)
                        .list(record -> {
                            Node node = record.get("c").asNode();
                            Course course = AppUtil.toCourse(node);
                            return course;
                        });
            });
        }
    }
}
