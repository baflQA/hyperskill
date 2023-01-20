package server;

import client.HttpMethod;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    private static final int PORT = 23456;
    private static final Path ROOT_PATH = Path.of(System.getProperty("user.dir") +
            File.separator + "src" + File.separator + "server" + File.separator + "data" + File.separator);

    public static void main(String[] args) {
        ROOT_PATH.toFile().mkdirs();

        String response="";
        while (!response.equals("exit")) {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server started!");
                try (Socket socket = serverSocket.accept();
                     DataInputStream input = new DataInputStream(socket.getInputStream());
                     DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                ) {
                    response = input.readUTF();
                    System.out.println("Received: " + response);
                    if (response.equals("exit")) {
                        output.writeUTF("exit");
                        break;
                    }
                    final String[] split = response.split("\\s+");
                    HttpMethod httpMethod = HttpMethod.valueOf(split[0]);
                    String fileName = split[1];

                    switch (httpMethod) {
                        case PUT: {
                            createFile(output, response, fileName);
                            break;
                        }
                        case GET: {
                            getFile(output, fileName);
                            break;
                        }
                        case DELETE: {
                            deleteFile(output, fileName);
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void deleteFile(DataOutputStream outputStream, String fileName) throws IOException {
        File file = ROOT_PATH.resolve(fileName).toFile();
        if (file.exists()) {
            Files.delete(file.toPath());
            outputStream.writeUTF("200");
            System.out.println("Sent: " + 200);
        } else {
            outputStream.writeUTF("404");
            System.out.println("Sent: " + 404);

        }
    }

    private static void getFile(DataOutputStream outputStream, String fileName) throws IOException {
        File file = ROOT_PATH.resolve(fileName).toFile();
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                String fileContent = new String(fileInputStream.readAllBytes());
                outputStream.writeUTF("200 " + fileContent);
                System.out.println("Sent: " + "200 " + fileContent);
            }
        } else {
            outputStream.writeUTF("404");
            System.out.println("Sent: " + 404);
        }
    }

    private static void createFile(DataOutputStream outputStream, String response, String fileName) throws IOException {
        if (ROOT_PATH.resolve(fileName).toFile().exists()) {
            outputStream.writeUTF("403");
        } else {
            final File rootDirectory = ROOT_PATH.toFile();
            rootDirectory.mkdirs();
            final File file = ROOT_PATH.resolve(fileName).toFile();
            file.createNewFile();
            String fileContent = response.split(fileName)[1].trim();
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(fileContent);
            }
            outputStream.writeUTF("200");
            System.out.println("Sent: " + 200);
        }
    }
}