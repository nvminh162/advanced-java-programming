package dao;

import entity.Doctor;
import org.neo4j.driver.Result;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;
import util.AppUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoctorDAOImpl implements DoctorDAO {
    @Override
    public boolean addDoctor(Doctor doctor) {
        String query = """
                CREATE (d:Doctor {doctor_id: $doctorID})
                SET d.name = $name,
                    d.phone = $phone,
                    d.speciality = $speciality
                RETURN d
                """;
        Map<String, Object> map = Map.of(
                "doctorID", doctor.getId(),
                "name", doctor.getName(),
                "phone", doctor.getPhone(),
                "speciality", doctor.getSpeciality()
        );
        try (var session = AppUtil.getSession()) {
            return session.executeWrite(tx -> {
                ResultSummary rs = tx.run(query, map).consume();
                return rs.counters().nodesCreated() > 0;
            });
        }
    }

    @Override
    public Doctor findDoctorById(String doctorID) {
        String query = """
                MATCH (d:Doctor {doctor_id: $doctorID})
                RETURN d
                """;
        Map<String, Object> map = Map.of("doctorID", doctorID);
        try (var session = AppUtil.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query, map);
                if(!result.hasNext()) return null;
                Node node = result.next().get("d").asNode();
                return AppUtil.toDoctor(node);
            });
        }
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        String query = """
                MERGE (d:Doctor {doctor_id: $doctorID})
                SET d.name = $name,
                    d.phone = $phone,
                    d.speciality = $speciality
                """;
        Map<String, Object> map = Map.of(
                "doctorID", doctor.getId(),
                "name", doctor.getName(),
                "phone", doctor.getPhone(),
                "speciality", doctor.getSpeciality()
        );
        try (var session = AppUtil.getSession()) {
            return session.executeWrite(tx -> {
                ResultSummary rs = tx.run(query, map).consume();
                return rs.counters().propertiesSet() > 0;
            });
        }
    }

    @Override
    public boolean deleteDoctorById(String doctorID) {
        String query = """
                MATCH (d:Doctor {doctor_id: $doctorID})
                DETACH DELETE d
                """;
        Map<String, Object> map = Map.of("doctorID", doctorID);
        try (var session = AppUtil.getSession()) {
            return session.executeWrite(tx -> {
                ResultSummary rs = tx.run(query, map).consume();
                return rs.counters().nodesDeleted() > 0;
            });
        }
    }

    @Override
    public int deleteAllDoctor() {
        String query = """
                MATCH (d:Doctor)
                DETACH DELETE d
                """;
        try (var session = AppUtil.getSession()) {
            return session.executeWrite(tx -> {
                ResultSummary rs = tx.run(query).consume();
                return rs.counters().nodesDeleted();
            });
        }
    }

    @Override
    public Map<String, Long> getNoOfDoctorsBySpeciality(String departmentName) {
        String query = """
                MATCH (d:Doctor)-[:BELONG_TO]->(dep:Department {name: $departmentName})
                RETURN d.speciality AS Speciality, count(d) AS NumOfDoctors
                """;
        Map<String, Object> map = Map.of("departmentName", departmentName);

        try (var session = AppUtil.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query, map);
                return result
                        .stream()
                        .collect(Collectors.toMap(
                                record -> record.get("Speciality").asString(),
                                record -> record.get("NumOfDoctors").asLong()
                        ));
            });
        }
    }

    @Override
    public List<Doctor> listDoctorsBySpeciality(String keyword) {
        String query = """
                CALL db.index.fulltext.queryNodes("txt_index_speciality", $keyword)
                YIELD node, score
                RETURN node;
                """;
        Map<String, Object> map = Map.of("keyword", keyword);

        try (var session = AppUtil.getSession()) {
            return session.executeRead(tx -> {
               Result result = tx.run(query, map);
               if(!result.hasNext()) return null;

               return result
                       .stream()
                       .map(record -> record.get("node").asNode())
                       .map(node -> AppUtil.toDoctor(node))
                       .collect(Collectors.toList());
            });
        }
    }

    @Override
    public boolean updateDiagnosis(String patientID, String doctorID, String newDiagnosis) {
        String query = """
                MATCH (p:Patient {patient_id: $patientID})-[b:BE_TREATED]->(d:Doctor {doctor_id: $doctorID})
                WHERE b.endDate IS NULL
                SET b.diagnosis = $newDiagnosis
                """;
        Map<String, Object> map = Map.of(
                "patientID", patientID,
                "doctorID", doctorID,
                "newDiagnosis", newDiagnosis
        );

        try (var session = AppUtil.getSession()) {
            return session.executeWrite(tx -> {
                ResultSummary rs = tx.run(query, map).consume();
                return rs.counters().propertiesSet() > 0;
            });
        }
    }
}
