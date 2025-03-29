package iuh.fit.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("DESKTOP-CTKG7K9", 7091);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in);) {
            while (true){
                System.out.println("Enter supplier id: ");
                int id = scanner.nextInt();
                dos.writeInt(id);

                String json = dis.readUTF();
                System.out.println("Supplier json data: " + json);
            }
        }
    }
}
