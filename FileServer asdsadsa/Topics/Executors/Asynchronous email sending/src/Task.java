import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {
    // Do not change it
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int messageCounts = scanner.nextInt();
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < messageCounts; ++i) {
            messages.add(scanner.next());
        }

        MailSender sender = new MockMailSender();
        asyncSend(sender, messages);
    }

    static void asyncSend(MailSender sender, List<String> messages) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    messages.forEach(message -> executorService.execute(() -> sender.send(message)));
    executorService.shutdown();
    }
}

// Do not change it
interface MailSender {
    void send(String message);
}

// The class simulatse mail sending
// Do not change it
class MockMailSender implements MailSender {
    public void send(String message) {
        System.out.println("Message " + message + " was sent");
    }
}
