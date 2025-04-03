import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
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
                        1. Thêm.
                        0. Thoát.
                        """;
                System.out.println(menu);
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> {

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
