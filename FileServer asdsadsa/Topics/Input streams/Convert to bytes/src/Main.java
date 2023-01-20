import java.io.InputStream;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        final byte[] bytes = inputStream.readAllBytes();
        for (byte aByte : bytes) {
            System.out.print(aByte);
        }
    }
}