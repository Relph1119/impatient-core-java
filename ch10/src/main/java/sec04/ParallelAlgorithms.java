package sec04;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ParallelAlgorithms {
    public static long occurrences(String word, Path path) {
        try {
            String contents = Files.readString(path);
            return Pattern.compile("\\PL+")
                    .splitAsStream(contents)
                    .filter(Predicate.isEqual(word))
                    .count();
        } catch (IOException ex) {
            return 0;
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path path = Paths.get(ParallelAlgorithms.class.getResource("../alice.txt").toURI());
        String contents = Files.readString(path);
        List<String> words = List.of(contents.split("\\PL+"));
        long result = words.parallelStream().filter(s -> s.startsWith("A")).count();
        System.out.println("Words starting with A: " + result);

        Path pathToRoot = Path.of(".");
        try (Stream<Path> paths = Files.walk(pathToRoot)) {
            long total = paths.parallel()
                    .mapToLong(p -> occurrences("String", p))
                    .sum();
            System.out.println("Occurrences of String: " + total);
        }

        int[] values = new int[1000000];
        Arrays.parallelSetAll(values, i -> i % 10);
        // Fills values with 0 1 2 3 4 5 6 7 8 9 0 1 2 ...
        System.out.println(Arrays.toString(Arrays.copyOf(values, 20)));
        String[] wordArray = words.toArray(new String[0]);
        Arrays.parallelSort(wordArray, Comparator.comparing(String::length));
        System.out.println(Arrays.toString(Arrays.copyOfRange(wordArray, wordArray.length / 2, wordArray.length / 2 + 20)));

        Arrays.parallelSort(values, values.length / 2, values.length);
        System.out.println(Arrays.toString(Arrays.copyOfRange(values, values.length / 2, values.length / 2 + 20)));
    }
}
