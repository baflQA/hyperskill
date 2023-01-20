import java.util.Scanner;

class NumbersFilter extends Thread {

    /* use it to read numbers from the standard input */
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (scanner.hasNext()) {
            int nextInt = scanner.nextInt();
            if (nextInt == 0) {
                return;
            } else if (nextInt % 2 == 0) {
                System.out.println(nextInt);
            }
        }
    }
}