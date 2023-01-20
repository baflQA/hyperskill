public class Main {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new RunnableWorker(), "worker-1");
        Thread thread2 = new Thread(new RunnableWorker(), "worker-2");
        Thread thread3 = new Thread(new RunnableWorker(), "worker-3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

// Don't change the code below       
class RunnableWorker implements Runnable {

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();

        if (name.startsWith("worker-")) {
            System.out.println("too hard calculations...");
        } else {
            return;
        }
    }
}