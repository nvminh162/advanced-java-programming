package daos;

import org.neo4j.driver.Driver;
import org.neo4j.driver.SessionConfig;

import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentDAO {
    private Driver driver;
    private SessionConfig sessionConfig;

    public DepartmentDAO(Driver driver, String dbName) {
        this.driver = driver;
        sessionConfig = SessionConfig
                .builder()
                .withDatabase(dbName)
                .build();
    }

    //13. Tổng số sinh viên đăng kí hoc khoá hoc của mỗi khoa
    public Map<String, Long> listNoStudentsByDepartment() {
        String query = """
                MATCH (s:Student)-[:ENROLLED]->(c:Course)-[:BELONGS_TO]->(d:Department)
                RETURN d.name, COUNT(*) AS `enrollment number`
                """;
        try (var session = driver.session(sessionConfig)) {
            return session.executeRead(tx -> {
                return tx.run(query)
                        .list()
                        .stream()
                        .collect(Collectors.toMap(
                                record -> record.get("d.name").asString(),
                                record -> record.get("enrollment number").asLong()
                        ));
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public boolean addDepartment(Department dept) {
//        String query = "CREATE (d:Department {dept_id: $id, name: $name, dean: $dean, building: $building, room: $room})";
//        Map<String, Object> params = Map.of(
//                "id", dept.getId(),
//                "name", dept.getName(),
//                "dean", dept.getDean(),
//                "building", dept.getBuilding(),
//                "room", dept.getRoom()
//
//        );
//        try (var session = driver.session(sessionConfig)) {
//            return session.executeWrite(tx -> {
//                ResultSummary result = tx.run(query, params).consume();
//                return result.counters().nodesCreated() > 0;
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // MATCH (d:Department) RETURN d.dean
//    public List<String> listDeans() {
//        String query = "MATCH (d:Department) RETURN d.dean\n";
//        try (Session session = driver.session(sessionConfig)) {
//            return session.executeRead(tx -> {
//                return tx.run(query)
//                        .list(record -> record.get("d.dean").asString());
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    // MATCH (d:Department {dept_id: "CS"}) RETURN d.dean
//    public String getDeanByDepartment(String deptId) {
//        String query = "MATCH (d:Department {dept_id: $id}) RETURN d.dean";
//        Map<String, Object> param = Map.of("id", deptId);
//        try (Session session = driver.session(sessionConfig)) {
//            return session.executeRead(tx -> {
//                Result result = tx.run(query, param);
//                if (!result.hasNext())
//                    return null;
//                return result.single().get("d.dean").asString();
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    // MATCH (s:Student)-[:ENROLLED]->(c:Course)-[:BELONG_TO]->(d:Department)
//    // RETURN d.name, COUNT(*) AS `enrollment number`
//    public Map<Department, Long> getEnrollmentNumber() {
//        String query = "MATCH (s:Student)-[:ENROLLED]->(c:Course)-[:BELONG_TO]->(d:Department)\n" +
//                "RETURN d.name, COUNT(*) AS `enrollment number`";
//        try (Session session = driver.session(sessionConfig)) {
//            return session.executeRead(tx -> {
//                return tx.run(query)
//                        .stream()
//                        .collect(Collectors.toMap(
//                                record -> {
//                                    Node node = record.get("d").asNode();
//                                    Department department = AppUtils.toDepartment(node);
//                                    return department;
//                                },
//                                record -> record.get("enrollment number").asLong(),
//                                (a, b) -> a, LinkedHashMap::new
//                        ));
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


}
