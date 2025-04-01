import dao.DoctorDAO;
import dao.DoctorDAOImpl;
import entity.Doctor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             Scanner sc = new Scanner(System.in)
        ) {
            while (true) {
                String cmd = dis.readUTF();
                switch (cmd) {
                    case "ADD_DOCTOR" -> {
                        String id = dis.readUTF();
                        String name = dis.readUTF();
                        String phone = dis.readUTF();
                        String speciality = dis.readUTF();
                        Doctor doctor = new Doctor(id, name, phone, speciality);
                        boolean result = doctorDAO.addDoctor(doctor);
                        oos.writeObject(result);
                        oos.flush();
                    }
                    case "EXIT" -> {
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