import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;
    Main() throws IOException {
        serverSocket = new ServerSocket(1234);
    }

    void sedAll(String message) {
        for(Client client : clients) {
            client.receive(message);
        }
    }

    public void run() {
        while(true) {
            System.out.println("Waiting...");

            try {
                // ждем клиента из сети
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                // создаем клиента на своей стороне
                clients.add(new Client(socket, this));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException {
       new Main().run();


    }
}
