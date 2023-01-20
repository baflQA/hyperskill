import java.util.Scanner;

class StringProcessor extends Thread {

    final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

    @Override
    public void run() {
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            if (!nextLine.toUpperCase().equals(nextLine)) {
                System.out.println(nextLine.toUpperCase());
            } else {
                System.out.println("FINISHED");
            }
        }
    }
}