package iuh.fit.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(7091)) {
            System.out.println("Ready!!!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client port: " + socket.getPort());
                System.out.println("Host name: " + socket.getInetAddress().getHostName());

                Thread thread = new Thread(new HandlingClient(socket));
                thread.start();
            }
        }
    }
}

class HandlingClient implements Runnable {
    private Socket socket;

    public HandlingClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in);) {
            while (true) {
                String message = dis.readUTF();
                System.out.println("Received client: " + message);

                System.out.println("Enter a message: ");
                message = scanner.nextLine();
                dos.writeUTF(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

