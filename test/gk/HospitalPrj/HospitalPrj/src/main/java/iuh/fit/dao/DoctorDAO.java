package iuh.fit.dao;

import iuh.fit.entity.Doctor;

import javax.print.Doc;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface DoctorDAO  {

    Doctor findDoctorById(String doctorId) ;

    boolean addDoctor(Doctor doctor) ;

    Map<String, Long> getNoOfDoctorsBySpeciality(String deptName) ;

    List<Doctor> listDoctorsBySpeciality(String keyword) ;

    boolean updateDiagnosis(String patientId, String doctorId, String newDiagnosis) ;
}
