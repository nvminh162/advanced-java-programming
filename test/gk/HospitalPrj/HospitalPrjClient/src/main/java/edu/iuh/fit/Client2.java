package edu.iuh.fit;


import iuh.fit.entity.Doctor;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client2 {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("paul", 3405);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in);
        ) {

            while (true) {
                System.out.println("\n===== Doctor Management System =====");
                System.out.println("1. Find Doctor by ID");
                System.out.println("2. Add New Doctor");
                System.out.println("3. Get Number of Doctors by Speciality and Department");
                System.out.println("4. List Doctors by Speciality");
                System.out.println("5. Update Patient Diagnosis");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> {
                        out.writeUTF("FIND_DOCTOR");

                        System.out.print("Enter Doctor ID: ");
                        String doctorId = scanner.nextLine();
                        out.writeUTF(doctorId);
                        out.flush();

                        Doctor doctor = (Doctor) in.readObject();
                        System.out.println(doctor);
                    }

                    case 2 -> {
                        out.writeUTF("ADD_DOCTOR");

                        System.out.print("Enter Doctor ID: ");
                        String doctorId = scanner.nextLine();
                        out.writeUTF(doctorId);

                        System.out.print("Enter Doctor Name: ");
                        String doctorName = scanner.nextLine();
                        out.writeUTF(doctorName);

                        System.out.print("Enter Speciality: ");
                        String speciality = scanner.nextLine();
                        out.writeUTF(speciality);

                        System.out.print("Enter Phone Number: ");
                        String phoneNumber = scanner.nextLine();
                        out.writeUTF(phoneNumber);
                        out.flush();

                        boolean isAdded = in.readBoolean();
                        if (isAdded) {
                            System.out.println("Doctor added successfully!");
                        } else {
                            System.out.println("Failed to add doctor!");
                        }
                    }

                    case 3 -> {
                        out.writeUTF("GET_DOCTOR_COUNT");

                        System.out.println("Enter department name: ");
                        String deptName = scanner.nextLine();
                        out.writeUTF(deptName);
                        out.flush();

                        Map<String, Long> doctorCount = (Map<String, Long>) in.readObject();
                        doctorCount.entrySet()
                                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
                    }

                    case 4 -> {
                        out.writeUTF("LIST_DOCTORS");

                        System.out.print("Enter Speciality Keyword: ");
                        String keyword = scanner.nextLine();
                        out.writeUTF(keyword);
                        out.flush();

                        List<Doctor> doctorList = (List<Doctor>) in.readObject();
                        if(doctorList != null)
                            doctorList.forEach(System.out::println);
                    }

                    case 5 -> {
                        out.writeUTF("UPDATE_DIAGNOSIS");

                        System.out.print("Enter Patient ID: ");
                        String patientId = scanner.nextLine();
                        out.writeUTF(patientId);

                        System.out.print("Enter Doctor ID: ");
                        String doctorId = scanner.nextLine();
                        out.writeUTF(doctorId);

                        System.out.print("Enter New Diagnosis: ");
                        String newDiagnosis = scanner.nextLine();
                        out.writeUTF(newDiagnosis);
                        out.flush();

                        boolean isUpdated = in.readBoolean();
                        if (isUpdated) {
                            System.out.println("Diagnosis updated successfully!");
                        } else {
                            System.out.println("Failed to update diagnosis!");
                        }
                    }

                    case 6 -> {
                        System.out.println("Exiting...");
                        out.writeUTF("EXIT");
                        out.flush();
                        return;
                    }

                    default -> System.out.println("Invalid choice! Please select again.");
                }
            }
        }
    }
}
