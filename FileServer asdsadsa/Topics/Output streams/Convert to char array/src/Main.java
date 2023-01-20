import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

class Converter {

    public static void main(String[] args) {
        int[] message = new int[] {114, 101, 97, 100, 32, 97, 98, 111, 117, 116, 32, 65, 83, 67, 73, 73};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (int code : message) {
            outputStream.write(code);
        }

        System.out.println(outputStream.toString());
    }
    public static char[] convert(String[] words) throws IOException {
        final String collect = Arrays.stream(words)
                .map(String::toCharArray)
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .map(String::new).collect(Collectors.joining());
        return collect.toCharArray();
    }
}