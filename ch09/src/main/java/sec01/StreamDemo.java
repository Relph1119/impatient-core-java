package sec01;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class StreamDemo {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Path path = Paths.get(TextIO.class.getResource("../alice.txt").toURI());
        try (InputStream in = Files.newInputStream(path)) {
            int firstByte = in.read();
            System.out.println("First byte: " + firstByte);
            byte[] moreBytes = new byte[1_000_000];
            int bytesRead = in.read(moreBytes);
            System.out.println("Bytes read: " + bytesRead);
        }

        byte[] allBytes = Files.readAllBytes(path);
        var contents = new String(allBytes, StandardCharsets.UTF_8);
        System.out.println(contents.substring(0, 50) + "...");

        byte[] helloBytes = {72, 101, 108, 108, 111, 10};
        path = Path.of("test.txt");
        try (OutputStream out = Files.newOutputStream(path)) {
            out.write(helloBytes);
        }

        var url = new URL("http://horstmann.com/index.html");
        try (InputStream in = url.openStream()) {
            Files.copy(in, Path.of("index.html"), StandardCopyOption.REPLACE_EXISTING);
        }

        var in = new ByteArrayInputStream(helloBytes);
        int n;
        do {
            n = in.read();
            System.out.println(n);
        } while (n != -1);

        var out = new ByteArrayOutputStream();
        out.write(helloBytes);
        System.out.println(out.toString(StandardCharsets.UTF_8));
    }
}
