package iuh.fit.server;

import iuh.fit.dao.SupplierDAO;
import iuh.fit.dao.SupplierDAOImpl;
import iuh.fit.model.Supplier;
import iuh.fit.util.MyJson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server2 {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(3405)) {
            System.out.println("Ready!!!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client port: " + socket.getPort());
                System.out.println("Host name: " + socket.getInetAddress().getHostName());

                Thread thread = new Thread(new HandlingClient2(socket));
                thread.start();
            }
        }
    }
}

class HandlingClient2 implements Runnable {
    private Socket socket;
    private SupplierDAO supplierDAO;

    public HandlingClient2(Socket socket) {
        this.socket = socket;
        this.supplierDAO = new SupplierDAOImpl();
    }

    @Override
    public void run() {
        try (
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in);) {
            while (true) {
                int supplierId = dis.readInt();
                Supplier supplier = supplierDAO.findById(supplierId);

                String json  = MyJson.toJson(supplier, Supplier.class);
                dos.writeUTF(json);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

