package iuh.fit.dao;


import iuh.fit.entity.Doctor;
import iuh.fit.util.AppUtils;
import org.neo4j.driver.Result;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoctorDAOImpl implements DoctorDAO {

    /**
     * Find a doctor by id
     * @param doctorId
     * @return
     */
    @Override
    public Doctor findDoctorById(String doctorId) {
        String query = "MATCH (d:Doctor {doctor_id: $doctorId}) RETURN d";
        try (var session = AppUtils.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query, Map.of("doctorId", doctorId));
                if (result.hasNext()) {
                    var record = result.next();
                    Node node = record.get("d").asNode();
                    return AppUtils.toDoctor(node);
                }
                return null;
            });
        }
    }

    /**
     * Add a new doctor
     * @param doctor
     * @return
     */
    @Override
    public boolean addDoctor(Doctor doctor) {
        try (var session = AppUtils.getSession()) {
            return session.executeWrite(tx -> {
                String query = "CREATE (d:Doctor {doctor_id: $doctor_id, name: $name, phone: $phone, speciality: $speciality}) RETURN d";

                ResultSummary summary = tx.run(query, AppUtils.toMap(doctor)).consume();

                return summary.counters().nodesCreated() > 0;
            });
        }
    }

    /**
     * @return
     */
    @Override
    public Map<String, Long> getNoOfDoctorsBySpeciality(String deptName) {
        try (var session = AppUtils.getSession()) {
            return session.executeRead(tx -> {
                String query = "MATCH (d:Doctor)-[:BELONG_TO]->(dep:Department {name: $deptName})\n" +
                        "RETURN d.speciality AS speciality, count(d) AS noOfDoctors";

                Result result = tx.run(query, Map.of("deptName", deptName));

                return result.stream()
                        .collect(Collectors.toMap(
                                record -> record.get("speciality").asString(),
                                record -> record.get("noOfDoctors").asLong()
                        ));
            });
        }
    }

    /**
     * CREATE FULLTEXT INDEX txt_index_speciality FOR (doc: Doctor) ON EACH [doc.speciality]
     * @param keywords
     * @return
     */
    @Override
    public List<Doctor> listDoctorsBySpeciality(String keywords) {
        try (var session = AppUtils.getSession()) {
            return session.executeRead(tx -> {
                String query = "CALL db.index.fulltext.queryNodes('txt_index_speciality', $keywords) YIELD node, score RETURN node as d";
                Result result = tx.run(query, Map.of("keywords", keywords));

                if(!result.hasNext())
                    return null;

                return result.stream()
                        .map(record -> record.get("d").asNode())
                        .map(node -> AppUtils.toDoctor(node))
                        .collect(Collectors.toList());
            });
        }
    }


    /**
     * Update the diagnosis of a patient treated by a doctor with a new diagnosis and end the treatment process
     * @param patientId
     * @param doctorId
     * @param newDiagnosis
     * @return
     */
    @Override
    public boolean updateDiagnosis(String patientId, String doctorId, String newDiagnosis) {
        try (var session = AppUtils.getSession()) {
            return session.executeWrite(tx -> {

                String query = "MATCH (p:Patient {patient_id: $patientId})-[r:BE_TREATED]->(d:Doctor {doctor_id: $doctorId})\n" +
                        "WHERE r.end_date IS NULL\n" +
                        "SET r.diagnosis = $diagnosis ";
                Map<String, Object> map = Map.of(
                        "patientId", patientId,
                        "doctorId", doctorId,
                        "diagnosis", newDiagnosis
                );

                ResultSummary summary = tx.run(query, map).consume();
                return summary.counters().propertiesSet() > 0;
            });
        }
    }

}
