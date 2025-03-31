package iuh.fit.util;

import iuh.fit.entity.Doctor;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;

import java.util.Map;

public class AppUtils {

    public static final String DB_NAME = "hospitalprj";

    /**
     * Get the driver to connect to the Neo4j database
     * @return
     */
    public static Driver getDriver() {
        String username = "neo4j";
        String password = "sapassword";
        String uri = "neo4j://localhost:7687";
        return GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    /**
     * @return
     */
    public static Session getSession() {
        return getDriver().session(SessionConfig.forDatabase(DB_NAME));
    }

    /**
     * Convert from Node to Doctor object
     * @param node
     * @return
     */
    public static Doctor toDoctor(Node node) {
        return new Doctor(
                node.get("doctor_id").asString(),
                node.get("name").asString(),
                node.get("phone").asString(),
                node.get("speciality").asString()
        );
    }


    /**
     * @param doctor
     * @return
     */
    public static Map<String, Object> toMap(Doctor doctor) {
        return Map.of(
                "doctor_id", doctor.getId(),
                "name", doctor.getName(),
                "phone", doctor.getPhone(),
                "speciality", doctor.getSpeciality()
        );
    }

}