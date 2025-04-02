import entity.Doctor;

import javax.print.Doc;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("paul", 3405);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner sc = new Scanner(System.in);
        ) {
            while (true) {
                String menu = """
                        ===== Doctor Management System =====
                        1. Thêm mới một bác sỹ.
                        2. Tìm bác sĩ theo mã
                        3. Cập nhật thông tin bác sĩ
                        4. Xoá bác sĩ theo mã
                        5. Xoá tất cả bác sĩ
                        6. Thống kê số bác sỹ theo từng chuyên khoa (speciality) của một khoa (department) nào đó khi biết tên khoa.
                        7. Dùng full-text search, tìm kiếm các bác sỹ theo chuyên khoa.
                        8. Cập nhật lại chẩn đoán (diagnosis) của một lượt điều trị khi biết mã số bác sỹ và mã số bệnh nhân.
                        0. Thoát.
                        """;
                System.out.println(menu);
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> {
                        out.writeUTF("addDoctor");

                        System.out.print("Enter doctor ID: ");
                        String doctorID = sc.nextLine();
                        out.writeUTF(doctorID);

                        System.out.print("Enter doctor name: ");
                        String doctorName = sc.nextLine();
                        out.writeUTF(doctorName);

                        System.out.print("Enter doctor phone: ");
                        String doctorPhone = sc.nextLine();
                        out.writeUTF(doctorPhone);

                        System.out.print("Enter doctor speciality: ");
                        String doctorSpeciality = sc.nextLine();
                        out.writeUTF(doctorSpeciality);

                        out.flush();

                        boolean result = in.readBoolean();
                        if (result) {
                            System.out.println("Add doctor success");
                        } else {
                            System.out.println("Add doctor failed");
                        }
                    }
                    case 2 -> {
                        out.writeUTF("findDoctorById");

                        System.out.print("Enter doctor ID: ");
                        String doctorID = sc.nextLine();
                        out.writeUTF(doctorID);

                        out.flush();

                        Doctor doctor = (Doctor) in.readObject();
                        System.out.println("Result find: "+ doctor);
                    }
                    case 3 -> {
                        out.writeUTF("updateDoctor");

                        System.out.print("Enter doctor ID: ");
                        String doctorID = sc.nextLine();
                        out.writeUTF(doctorID);

                        System.out.print("Enter doctor name: ");
                        String doctorName = sc.nextLine();
                        out.writeUTF(doctorName);

                        System.out.print("Enter doctor phone: ");
                        String doctorPhone = sc.nextLine();
                        out.writeUTF(doctorPhone);

                        System.out.print("Enter doctor speciality: ");
                        String doctorSpeciality = sc.nextLine();
                        out.writeUTF(doctorSpeciality);

                        out.flush();

                        boolean result = in.readBoolean();
                        if (result) {
                            System.out.println("Update doctor success");
                        } else {
                            System.out.println("Update doctor failed");
                        }
                    }
                    case 4 -> {
                        out.writeUTF("deleteDoctorById");

                        System.out.print("Enter doctor ID: ");
                        String doctorID = sc.nextLine();
                        out.writeUTF(doctorID);

                        out.flush();

                        boolean result = in.readBoolean();
                        if (result) {
                            System.out.println("Delete doctor success");
                        } else {
                            System.out.println("Delete doctor failed");
                        }
                    }
                    case 5 -> {
                        out.writeUTF("deleteAllDoctor");
                        int result = in.readInt();
                        if (result > 0) {
                            System.out.println("Delete all doctor success (include: " + result + ")");
                        } else {
                            System.out.println("Delete all doctor failed");
                        }
                    }
                    case 6 -> {
                        out.writeUTF("getNoOfDoctorsBySpeciality");

                        System.out.print("Enter departmentName: ");
                        String departmentName = sc.nextLine();
                        out.writeUTF(departmentName);

                        out.flush();

                        Map<String, Long> doctorCount = (Map<String, Long>) in.readObject();
                        doctorCount
                                .entrySet()
                                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue())
                        );
                    }
                    case 7 -> {
                        out.writeUTF("listDoctorsBySpeciality");

                        System.out.print("Enter keyword: ");
                        String keyword = sc.nextLine();
                        out.writeUTF(keyword);

                        out.flush();

                        List<Doctor> doctors = (List<Doctor>) in.readObject();
                        doctors.forEach(doctor -> System.out.println(doctor));
                    }
                    case 8 -> {
                        out.writeUTF("updateDiagnosis");

                        System.out.print("Enter patient id: ");
                        String patientID = sc.nextLine();
                        out.writeUTF(patientID);

                        System.out.print("Enter doctor id: ");
                        String doctorID = sc.nextLine();
                        out.writeUTF(doctorID);

                        System.out.print("Enter diagnosis: ");
                        String diagnosis = sc.nextLine();
                        out.writeUTF(diagnosis);

                        out.flush();

                        boolean result = in.readBoolean();
                        if (result) {
                            System.out.println("Update diagnosis success");
                        } else {
                            System.out.println("Update diagnosis failed");
                        }
                    }
                    case 0 -> {
                        out.writeUTF("close");
                        out.flush();
                        System.out.println("Closing connection");
                        return;
                    }
                    default -> System.out.println("Invalid choice! pls try again.");
                }
            }
        }
    }
}
