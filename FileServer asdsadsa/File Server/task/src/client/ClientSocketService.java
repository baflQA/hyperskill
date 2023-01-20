package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketService {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;

    public ClientSocketService() {
        System.out.println("Client started!");
    }

    public String sendToServer(String message) throws IOException {
        try (Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
            outputStream.writeUTF(message);
            System.out.println("The request was sent.");
            return inputStream.readUTF();
        }
    }
}
