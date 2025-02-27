package sec12;

import sec08.CollectingResults;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ReductionDemo {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Integer[] digits = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2, 3, 8, 4, 6, 2, 6,
                4, 3, 3, 8, 3, 2, 7, 9, 5, 0, 2, 8, 8, 4, 1, 9, 7, 1, 6, 9, 3, 9, 9, 3, 7, 5, 1, 0,
                5, 8, 2, 0, 9, 7, 4, 9, 4, 4, 5, 9, 2, 3, 0, 7, 8, 1, 6, 4, 0, 6, 2, 8, 6};
        Stream<Integer> values = Stream.of(digits);
        Optional<Integer> sum = values.reduce(Integer::sum);
        System.out.println("sum: " + sum);

        values = Stream.empty();
        sum = values.reduce(Integer::sum); // Or values.reduce(Integer::sum);
        System.out.println("sum: " + sum);

        values = Stream.of(digits);
        Integer sum2 = values.reduce(0, Integer::sum);
        System.out.println("sum2: " + sum2);

        values = Stream.empty();
        Integer sum3 = values.reduce(0, Integer::sum);
        System.out.println("sum3: " + sum3);

        Path path = Paths.get(ReductionDemo.class.getResource("../alice.txt").toURI());
        String contents = Files.readString(path);
        List<String> words = List.of(contents.split("\\PL+"));

        int result = words.stream().reduce(
                0,
                (total, word) -> total + word.length(),
                Integer::sum);
        System.out.println("result: " + result);

        int result2 = words.stream().mapToInt(String::length).sum();
        System.out.println("result2: " + result2);
    }
}
