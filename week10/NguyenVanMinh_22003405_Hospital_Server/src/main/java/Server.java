import dao.DoctorDAO;
import dao.DoctorDAOImpl;
import entity.Doctor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Server {
    private static final int PORT = 3405;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server listening on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New connection from " + socket.getInetAddress() + ": " + socket.getPort());

                Thread thread = new Thread(new HandlingClient(socket));
                thread.start();
            }
        }
    }
}

class HandlingClient implements Runnable {
    private Socket socket;
    private DoctorDAO doctorDAO;

    public HandlingClient (Socket socket) {
        this.socket = socket;
        doctorDAO = new DoctorDAOImpl();
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             Scanner sc = new Scanner(System.in)
        ) {
            while (true) {
                String cmd = in.readUTF();
                switch (cmd) {
                    case "addDoctor" -> {
                        String id = in.readUTF();
                        String name = in.readUTF();
                        String phone = in.readUTF();
                        String speciality = in.readUTF();
                        Doctor doctor = new Doctor(id, name, phone, speciality);
                        boolean result = doctorDAO.addDoctor(doctor);
                        System.out.println(doctor + ": " + result);
                        out.writeBoolean(result);
                        out.flush();
                    }
                    case "findDoctorById" -> {
                        String id = in.readUTF();
                        Doctor doctor = doctorDAO.findDoctorById(id);
                        System.out.println(doctor);
                        out.writeObject(doctor);
                        out.flush();
                    }
                    case "updateDoctor" -> {
                        String id = in.readUTF();
                        String name = in.readUTF();
                        String phone = in.readUTF();
                        String speciality = in.readUTF();
                        Doctor doctor = new Doctor(id, name, phone, speciality);
                        boolean result = doctorDAO.updateDoctor(doctor);
                        System.out.println(doctor + ": " + result);
                        out.writeBoolean(result);
                        out.flush();
                    }
                    case "deleteDoctorById" -> {
                        String id = in.readUTF();
                        boolean result = doctorDAO.deleteDoctorById(id);
                        System.out.println(result);
                        out.writeBoolean(result);
                        out.flush();
                    }
                    case "deleteAllDoctor" -> {
                        int count = doctorDAO.deleteAllDoctor();
                        System.out.println(count);
                        out.writeInt(count);
                        out.flush();
                    }
                    case "getNoOfDoctorsBySpeciality" -> {
                        String departmentName = in.readUTF();
                        Map<String, Long> doctorCount = doctorDAO.getNoOfDoctorsBySpeciality(departmentName);
                        System.out.println(doctorCount);
                        out.writeObject(doctorCount);
                        out.flush();
                    }
                    case "listDoctorsBySpeciality" -> {
                        String keyword = in.readUTF();
                        List<Doctor> doctors = doctorDAO.listDoctorsBySpeciality(keyword);
                        System.out.println(doctors);
                        out.writeObject(doctors);
                        out.flush();
                    }
                    case "updateDiagnosis" -> {
                        String patientId = in.readUTF();
                        String doctorId = in.readUTF();
                        String diagnosis = in.readUTF();
                        boolean result = doctorDAO.updateDiagnosis(patientId, doctorId, diagnosis);
                        System.out.println(result);
                        out.writeBoolean(result);
                        out.flush();
                    }
                    case "close" -> {
                        System.out.println("Client disconnected!");
                        socket.close();
                        return;
                    }
                    default -> System.out.println("Invalid command received: " + cmd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}