package dao;

import entity.Doctor;

import java.util.List;
import java.util.Map;

public interface DoctorDAO {
    boolean addDoctor(Doctor doctor);

    Doctor findDoctorById(String doctorID);

    boolean updateDoctor(Doctor doctor);

    boolean deleteDoctorById(String doctorID);

    int deleteAllDoctor();

    Map<String, Long>  getNoOfDoctorsBySpeciality(String departmentName); //Key: Chuyên khoa; Value: Số bác sỹ

    List<Doctor> listDoctorsBySpeciality(String keyword);

    boolean updateDiagnosis(String patientID, String doctorID, String newDiagnosis);
}
