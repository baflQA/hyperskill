package client;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter action (1 - get a file, 2 - create a file, 3 - delete a file):");
            String command = scanner.nextLine();
            ClientSocketService clientSocketService = new ClientSocketService();
            if (command.equals("exit")) {
                clientSocketService.sendToServer("exit");
                break;
            }
            System.out.println("Enter filename:");
            String fileName = scanner.nextLine();
            final HttpMethod httpMethod = HttpMethod.fromActionNumber(Integer.parseInt(command));
            String fileContent = "";
            if (httpMethod.equals(HttpMethod.PUT)) {
                System.out.println("Enter file content:");
                fileContent = scanner.nextLine();
            }
            handleCommand(clientSocketService, httpMethod, fileName, fileContent);
        }
    }

    private static void handleCommand(ClientSocketService socketService, HttpMethod method, String fileName, String fileContent) throws IOException {
        switch (method) {
            case GET: {
                get(socketService, fileName);
                break;
            }
            case PUT: {
                create(socketService, fileName, fileContent);
                break;
            }
            case DELETE: {
                delete(socketService, fileName);
                break;
            }
            default:
                throw new IllegalStateException();
        }
    }

    private static void create(ClientSocketService socketService, String fileName, String fileContent) throws IOException {
        String message = HttpMethod.PUT.name() + " " + fileName + " " + fileContent;
        final String response = socketService.sendToServer(message);
        if (response.equals("200")) {
            System.out.println("The response says that the file was created!");
        } else if (response.equals("403")) {
            System.out.println("The response says that creating the file was forbidden!");
        }
    }

    private static void get(ClientSocketService socketService, String fileName) throws IOException {
        String response = socketService.sendToServer(HttpMethod.GET.name() + " " + fileName);
        String[] split = response.split(" ");
        String responseCode = split[0];
        if (responseCode.equals("200")) {
            System.out.println("The content of the file is: " + split[1]);
        } else if (response.equals("404")) {
            System.out.println("The response says that the file was not found!");
        }
    }

    private static void delete(ClientSocketService socketService, String fileName) throws IOException {
        String response = socketService.sendToServer(HttpMethod.DELETE.name() + " " + fileName);
        if (response.equals("200")) {
            System.out.println("The response says that the file was successfully deleted!");
        } else if (response.equals("404")) {
            System.out.println("The response says that the file was not found!");
        }
    }
}