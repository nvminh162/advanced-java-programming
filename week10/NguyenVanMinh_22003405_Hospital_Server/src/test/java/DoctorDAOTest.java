import dao.DoctorDAO;
import dao.DoctorDAOImpl;
import entity.Doctor;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorDAOTest {
    private DoctorDAO doctorDAO;

    @BeforeAll
    void setup() {
        doctorDAO = new DoctorDAOImpl();
    }

    @Test
    void addDoctorTest() {
        Doctor doctor = new Doctor("22003405", "Nguyen Van Minh", "0353999798", "Software Engineering");
        boolean isAdded = doctorDAO.addDoctor(doctor);
        assertTrue(isAdded, "Doctor should be added successfully.");
    }

    @AfterAll
    void tearDown() {
        doctorDAO = null;
    }
}
