package sec05;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        var settings = new Properties();
        settings.put("width", "200");
        settings.put("title", "Hello, World!");
        Path path = Path.of("demo.properties");
        try (OutputStream out = Files.newOutputStream(path)) {
            settings.store(out, "Program Properties");
        }

        settings = new Properties();
        try (InputStream in = Files.newInputStream(path)) {
            settings.load(in);
        }
        System.out.println(settings);

        String title = settings.getProperty("title", "New Document");
        String height = settings.getProperty("height", "100");
        System.out.println(title);
        System.out.println(height);
        System.out.println();
        System.out.println("System properties");
        Properties sysprops = System.getProperties();
        sysprops.forEach((k, v) -> System.out.printf("%s=%s\n", k, v));
    }
}
