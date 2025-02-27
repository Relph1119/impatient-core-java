package sec09;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

public class PreferencesDemo {
    public static void main(String[] args) throws BackingStoreException, IOException, InvalidPreferencesFormatException {
        Preferences root = Preferences.userRoot();
        Preferences node = root.node("/com/horstmann/corejava");
        Path path = Path.of("prefs.xml");
        if (args.length > 0) {
            switch (args[0]) {
                case "--clear" -> node.removeNode();
                case "--export" -> {
                    try (OutputStream out = Files.newOutputStream(path)) {
                        node.exportSubtree(out);
                    }
                    System.out.println("Preferences exported to " + path.toAbsolutePath());
                }
                case "--import" -> {
                    try (InputStream in = Files.newInputStream(path)) {
                        Preferences.importPreferences(in);
                    }
                    System.out.println("Preferences imported from " + path.toAbsolutePath());
                }
            }
        } else {
            String username = node.get("username", "");
            if (username.equals("")) {
                Console console = System.console();
                username = console.readLine("What is your name? ");
                node.put("username", username);
            } else System.out.println("Hello, " + username);
        }
    }
}
