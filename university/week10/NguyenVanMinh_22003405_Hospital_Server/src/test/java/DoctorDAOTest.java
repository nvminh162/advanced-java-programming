import dao.DoctorDAO;
import dao.DoctorDAOImpl;
import entity.Doctor;
import org.junit.jupiter.api.*;

import javax.print.Doc;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Doctor doctor = new Doctor("22003405", "Nguyen Van Minh", "0353.999.798", "SE Senior");
        boolean result = doctorDAO.addDoctor(doctor);
        assertTrue(result, "Doctor should be added successfully.");
    }

    @Test
    void findDoctorByIdTest() {
        Doctor doctor = doctorDAO.findDoctorById("22003405");
        assertEquals("Nguyen Van Minh", doctor.getName());
        assertEquals("0353.999.798", doctor.getPhone());
        assertEquals("SE Senior", doctor.getSpeciality());
    }

    @Test
    void updateDoctorTest() {
        Doctor doctor = new Doctor("22003405", "Minh Nguyen", "0987.654.321", "Tech Lead");
        boolean result = doctorDAO.updateDoctor(doctor);
        assertTrue(result, "Doctor should be updated successfully.");
    }

    @Test
    void deleteDoctorByIdTest() {
        boolean result = doctorDAO.deleteDoctorById("22003405");
        assertTrue(result, "Doctor should be deleted successfully.");
    }

    @AfterAll
    void tearDown() {
        doctorDAO = null;
    }
}
