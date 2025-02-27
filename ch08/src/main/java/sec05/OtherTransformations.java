package sec05;

import sec01.CountLongWords;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OtherTransformations {
    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        // 转变为可变数组
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());
        System.out.print(title + ": ");
        if (firstElements.size() <= SIZE)
            System.out.println(firstElements);
        else {
            firstElements.remove(SIZE);
            String out = firstElements.toString();
            System.out.println(out.substring(0, out.length() - 1) + ", ...]");
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Stream<String> uniqueWords = Stream.of("merrily", "merrily", "merrily", "gently")
                .distinct();
        show("uniqueWords", uniqueWords);

        Path path = Paths.get(OtherTransformations.class.getResource("../alice.txt").toURI());
        String contents = Files.readString(path);
        List<String> words = List.of(contents.split("\\PL+"));
        show("words", words.stream());

        Stream<String> distinct = words.stream().distinct();
        show("distinct", distinct);

        Stream<String> sorted = words.stream().sorted();
        show("sorted", sorted);

        Stream<String> distinctSorted = words.stream().distinct().sorted();
        show("distinctSorted", distinctSorted);

        Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed());
        show("longestFirst", longestFirst);

        Object[] powers = Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("Fetching " + e)).limit(20).toArray();
        System.out.println(Arrays.toString(powers));
    }
}
