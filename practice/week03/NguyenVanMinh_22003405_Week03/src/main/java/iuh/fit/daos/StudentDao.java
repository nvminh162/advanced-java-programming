package iuh.fit.daos;

import org.neo4j.driver.Driver;
import org.neo4j.driver.SessionConfig;

import java.util.Map;

public class StudentDao {

    private final Driver driver;
    private final SessionConfig sessionConfig;

    public StudentDao(Driver driver, String dbName) {
        this.driver = driver;
        sessionConfig = SessionConfig.builder().withDatabase(dbName).build();
    }

    public boolean enrollCourse(String studentId, String courseId) {
        String query = """
            MATCH (s:Student {student_id: $studentId}), (c:Course {course_id: $courseId})
            CREATE (s)-[:ENROLLED_IN]->(c)
            RETURN count(s) AS enrolledCount
        """;
        Map<String, Object> params = Map.of(
                "studentId", studentId,
                "courseId", courseId
        );

        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                var result = tx.run(query, params);
                return result.single().get("enrolledCount").asInt() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unenrollCourse(String studentId, String courseId) {
        String query = """
            MATCH (s:Student {student_id: $studentId})-[r:ENROLLED_IN]->(c:Course {course_id: $courseId})
            DELETE r
            RETURN count(r) AS deletedCount
        """;
        Map<String, Object> params = Map.of(
                "studentId", studentId,
                "courseId", courseId
        );

        try (var session = driver.session(sessionConfig)) {
            return session.executeWrite(tx -> {
                var result = tx.run(query, params);
                return result.single().get("deletedCount").asInt() > 0;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEnrollment(String studentId, String courseId, double grade) {
        String query = """
            MATCH (s:Student {student_id: $studentId})-[r:ENROLLED_IN]->(c:Course {course_id: $courseId})
            SET r.grade = $grade
            RETURN count(r) AS updatedCount
        """;
        Map<String, Object> params = Map.of(
                "studentId", studentId,
                "courseId", courseId,
                "grade", grade
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


//    public Enrollment findEnrollment(String studentId, String courseId){
//        return null;
//    }
}
