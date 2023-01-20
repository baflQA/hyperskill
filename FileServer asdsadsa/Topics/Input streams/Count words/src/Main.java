import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final String s = reader.readLine().trim();
            if (s.isBlank()) {
                System.out.println(0);
            } else {
                final String[] split = s.split("\\W+");
                System.out.println(split.length);
            }
        }
    }
}