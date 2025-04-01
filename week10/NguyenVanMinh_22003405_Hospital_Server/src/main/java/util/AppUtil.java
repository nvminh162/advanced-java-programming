package util;

import entity.Doctor;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;

import javax.print.Doc;
import java.util.Map;

public class AppUtil {
    private static final String DB_NAME = "minh22003405hospital";

    /**
     *
     * @return
     */
    public static Driver getDiver() {
        String username = "neo4j";
        String password = "sapassword";
        String uri = "neo4j://localhost:7687";
        return GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    /**
     *
     * @return
     */
    public static Session getSession() {
        return getDiver().session(SessionConfig.forDatabase(DB_NAME));
    }

    public static Map<String, Object> toMapDoctor(Doctor doctor) {
        return Map.of(
                "doctor_id", doctor.getId(),
                "name", doctor.getName(),
                "phone", doctor.getPhone(),
                "speciality", doctor.getSpeciality()
        );
    }

    public static Doctor toDoctor(Node node) {
        return new Doctor(
                node.get("doctor_id").asString(),
                node.get("name").asString(),
                node.get("phone").asString(),
                node.get("speciality").asString()
        );
    }
}
