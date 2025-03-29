package iuh.fit;

import iuh.fit.entity.Doctor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client2 {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("DESKTOP-GFO363L", 9090);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in);
        ) {

            while (true) {
                System.out.println("\n===== Doctor Management System =====");
                System.out.println("1. Find Doctor by ID");
                System.out.println("2. Add New Doctor");
                System.out.println("3. Get Number of Doctors by Speciality");
                System.out.println("4. List Doctors by Speciality");
                System.out.println("5. Update Patient Diagnosis");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Doctor ID: ");
                        String doctorId = scanner.nextLine();
                        out.writeUTF("FIND_DOCTOR");
                        out.writeUTF(doctorId);
                        out.flush();
                        Doctor doctor = (Doctor) in.readObject();
                        System.out.println(doctor);
                    }

                    case 2 -> {}

                    case 3 -> {
                        out.writeUTF("GET_DOCTOR_COUNT");
                        out.flush();
                        Map<String, Long> doctorCount = (Map<String, Long>) in.readObject();
                        doctorCount.entrySet()
                                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
                    }

                    case 4 -> {
                        System.out.print("Enter Speciality Keyword: ");
                        String keyword = scanner.nextLine();
                        out.writeUTF("LIST_DOCTORS");
                        out.writeUTF(keyword);
                        out.flush();
                        List<Doctor> doctorList = (List<Doctor>) in.readObject();
                        doctorList.forEach(System.out::println);
                    }

                    case 5 -> {}

                    case 6 -> {
                        System.out.println("Exiting...");
                        out.writeUTF("EXIT");
                        out.flush();
                        return;
                    }

                    default ->
                        System.out.println("Invalid choice! Please select again.");
                }
            }
//            while (true) {
//                System.out.println("Enter doctorID : ");
//                String id = scanner.nextLine();
//                out.writeUTF(id);
//                out.flush();
//                Doctor doctor = (Doctor) in.readObject();
//                System.out.println(doctor);
//            }
        }
    }
}


//public interface DoctorDAO {
//
//    Doctor findDoctorById(String doctorId);
//
//    boolean addDoctor(Doctor doctor);
//
//    Map<String, Long> getNoOfDoctorsBySpeciality();
//
//    List<Doctor> listDoctorsBySpeciality(String keyword);
//
//    boolean updateDiagnosis(String patientId, String doctorId, String newDiagnosis);
//}