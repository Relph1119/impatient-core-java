package sec02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        Path tempPath = Files.createTempDirectory("corejava");

        Path workDir = Files.createDirectory(tempPath.resolve("work"));
        System.out.println(workDir);
        Path tempFile = Files.createTempFile(workDir, "test", ".txt");
        System.out.println(tempFile);
        Files.writeString(tempFile, "Hello");
        Files.copy(tempFile, workDir.resolve("hello.txt"));

        Path target2 = workDir.resolve("hello2.txt");
        Files.move(tempFile, target2, StandardCopyOption.ATOMIC_MOVE);
        boolean deleted = Files.deleteIfExists(target2);
        if (deleted) System.out.println("Deleted " + target2);
    }
}
