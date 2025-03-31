package iuh.fit;


import iuh.fit.dao.DoctorDAO;
import iuh.fit.dao.DoctorDAOImpl;
import iuh.fit.entity.Doctor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Server2 {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(3405)) {
            System.out.println("Server is ready!!!");
            while (true) {//24/7
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress());
                System.out.println(socket.getPort());

                Thread thread = new Thread(new HandlingClient2(socket));
                thread.start();
            }
        }
    }
}

class HandlingClient2 implements Runnable {
    private Socket socket;
    private DoctorDAO doctorDAO;

    public HandlingClient2(Socket socket) {
        this.socket = socket;
        doctorDAO = new DoctorDAOImpl();
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Received request from Client
                String command = in.readUTF();

                switch (command) {
                    case "FIND_DOCTOR" -> {
                        String doctorId = in.readUTF();

                        Doctor doctor = doctorDAO.findDoctorById(doctorId);
                        out.writeObject(doctor);
                        out.flush();
                    }

                    case "ADD_DOCTOR" -> {
                        String id = in.readUTF();
                        String name = in.readUTF();
                        String speciality = in.readUTF();
                        String phone = in.readUTF();

                        Doctor doctor = new Doctor(id, name, phone, speciality);
                        boolean result = doctorDAO.addDoctor(doctor);

                        out.writeBoolean(result);
                        out.flush();
                    }


                    case "GET_DOCTOR_COUNT" -> {
                        String deptName = in.readUTF();

                        Map<String, Long> doctorCount = doctorDAO.getNoOfDoctorsBySpeciality(deptName);
                        out.writeObject(doctorCount);
                        out.flush();
                    }
                    case "LIST_DOCTORS" -> {
                        String keywords = in.readUTF();
                        System.out.println(keywords);

                        List<Doctor> doctors = doctorDAO.listDoctorsBySpeciality(keywords);
//                      System.out.println(doctors);
                        out.writeObject(doctors);
                        out.flush();
                    }

                    case "UPDATE_DIAGNOSIS" -> {
                        String patientId = in.readUTF();
                        String doctorId = in.readUTF();
                        String diagnosis = in.readUTF();

                        boolean result = doctorDAO.updateDiagnosis(patientId, doctorId, diagnosis);

                        out.writeBoolean(result);
                        out.flush();
                    }

                    case "EXIT" -> {
                        System.out.println("Client disconnected.");
                        socket.close();
                        return;
                    }
                    default -> System.out.println("Invalid command received: " + command);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}