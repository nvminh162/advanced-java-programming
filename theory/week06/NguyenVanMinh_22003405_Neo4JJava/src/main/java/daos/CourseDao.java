package daos;

import models.Course;
import utils.AppUtils;
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

    // CREATE (n:Course {course_id:"JAVA01", name:"Basic Java Programming", hours:30});
    public boolean addCourse(Course course) {
        String query =
                """
                CREATE (n:Course {course_id:$id, name:$name, hours:$hours})
                """;
        Map<String, Object> params = Map.of(
                "id", course.getId(),
                "name", course.getName(),
                "hours", course.getHours()
        );
        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                ResultSummary summary = tx.run(query, params).consume();
                return summary.counters().nodesCreated() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //MERGE (c:Course{course_id:"JAVA01", name:"Basic Java",hours:30})
    public boolean updateCourse(Course course) {
        String query =
                """
                MERGE (n:Course {course_id: $id})
                SET n.name = $name, n.hours = $hours
                """;
        Map<String, Object> params = Map.of(
                "id", course.getId(),
                "name", course.getName(),
                "hours", course.getHours()
        );
        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                ResultSummary summary = tx.run(query, params).consume();
                return summary.counters().propertiesSet() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //MATCH (c:Course{course_id:"JAVA01"}) DETACH DELETE c;
    public boolean deleteCourse(String id) {
        String query =
                """
                MATCH (c:Course{course_id:$id}) DETACH DELETE c
                """;
        Map<String, Object> param = Map.of("id", id);
        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                ResultSummary summary = tx.run(query, param).consume();
                return summary.counters().nodesDeleted() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int deleteAllCourses() {
        String query =
                """
                MATCH (c:Course) DETACH DELETE c
                """;
        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                ResultSummary summary = tx.run(query).consume();
                return summary.counters().nodesDeleted();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //MATCH (c:Course{course_id:"JAVA01"}) RETURN c;
    public Course findById(String id) {
        String query =
                """
                MATCH (c:Course {course_id:$id}) RETURN c
                """;
        Map<String, Object> param = Map.of("id", id);
        try (var session = driver.session(sessionConfig)) {
            return session.executeRead(tx -> {
                Result result = tx.run(query, param);
                if (!result.hasNext())
                    return null;
                Node node = result.single().get("c").asNode();
                Course course = AppUtils.toCourse(node);
                return course;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Course> listCourse(int skip, int limit) {
        String query =
                """
                MATCH (c:Course) SKIP $skip LIMIT $limit RETURN c
                """;
        Map<String, Object> params = Map.of(
                "skip", skip,
                "limit", limit
        );
        try (var session = driver.session(sessionConfig)) {
            return session.executeRead(tx -> tx
                    .run(query, params)
                    .list(record -> {
                        Node node = record.get("c").asNode();
                        Course course = AppUtils.toCourse(node);
                        return course;
                    }));
        }
    }
}
