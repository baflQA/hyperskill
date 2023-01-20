import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/* Do not change this class */
class Message {
  final String text;
  final String from;
  final String to;

  Message(String from, String to, String text) {
    this.text = text;
    this.from = from;
    this.to = to;
  }
}

/* Do not change this interface */
interface AsyncMessageSender {
  void sendMessages(Message[] messages);

  void stop();
}

class AsyncMessageSenderImpl implements AsyncMessageSender {
  private final ExecutorService executor = Executors.newFixedThreadPool(4);
  private final int repeatFactor;

  public AsyncMessageSenderImpl(int repeatFactor) {
    this.repeatFactor = repeatFactor;
  }

  @Override
  public void sendMessages(Message[] messages) {
    for (Message msg : messages) {
      IntStream.range(0, repeatFactor).forEach(i -> executor.submit(() -> {
        System.out.printf("(%s>%s): %s\n", msg.from, msg.to, msg.text); // do not change it
      }));
    }
  }

  @Override
  public void stop() {
    try {
      executor.awaitTermination(1, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    finally {
      System.out.println("Completed.");
    }
  }
}
