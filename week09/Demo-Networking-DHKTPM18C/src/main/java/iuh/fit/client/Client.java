package iuh.fit.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("DESKTOP-CTKG7K9", 7091);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in);) {
            while (true){
                System.out.println("Enter a message: ");
                String message = scanner.nextLine();
                dos.writeUTF(message);

                message = dis.readUTF();
                System.out.println("Received server: " + message);
            }
        }
    }
}
