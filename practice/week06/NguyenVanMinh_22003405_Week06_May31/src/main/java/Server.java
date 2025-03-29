import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(3405)){
            System.out.println("Server is reaady!");
            while (true){
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new HandlerSocket(socket));
                thread.start();
            }
        }

    }
}

class HandlerSocket implements  Runnable {
    private Socket socket;
    public HandlerSocket(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        System.out.println(socket.getInetAddress().getHostName());
        System.out.println(socket.getPort());
    }
}