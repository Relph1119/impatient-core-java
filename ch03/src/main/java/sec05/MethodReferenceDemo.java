package sec05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MethodReferenceDemo {
    public static void main(String[] args) {
        String[] strings = { "Mary", "had", "a", "little", "lamb" };
        Arrays.sort(strings, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(strings));
        var enemies = new ArrayList<>(Arrays.asList("Malfoy", "Crabbe", "Goyle", null));
        enemies.removeIf(Objects::isNull);
        enemies.forEach(System.out::println);
    }
}
