package sec09;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ReadDir {
    public static void main(String[] args) throws IOException, InterruptedException {
        Process process = new ProcessBuilder("/bin/ls", "-l").directory(Path.of("/tmp").toFile()).start();
        try (var in = new Scanner(process.getInputStream())) {
            while (in.hasNextLine())
                System.out.println(in.nextLine());
        }
        //int result = p.waitFor();
        //System.out.println("Exit value: " + result);
        process.onExit().thenAccept(
                p -> System.out.println("Exit value: " + p.exitValue()));
    }
}
