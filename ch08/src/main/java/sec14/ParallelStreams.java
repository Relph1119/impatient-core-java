package sec14;

import sec08.CollectingResults;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ParallelStreams {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Path path = Paths.get(CollectingResults.class.getResource("../alice.txt").toURI());
        String contents = Files.readString(path);
        List<String> words = List.of(contents.split("\\PL+"));

        // Very bad code ahead
        int[] shortWords = new int[12];
        words.parallelStream().forEach(
                s -> {
                    if (s.length() < 12) shortWords[s.length()]++;
                });
        System.out.println(Arrays.toString(shortWords));

        // Try again--the result will likely be different (and also wrong)
        Arrays.fill(shortWords, 0);
        words.parallelStream().forEach(
                s -> {
                    if (s.length() < 12) shortWords[s.length()]++;
                });
        System.out.println(Arrays.toString(shortWords));

        // Remedy: Group and count 
        Map<Integer, Long> shortWordCounts =
                words.parallelStream()
                        .filter(s -> s.length() < 12)
                        .collect(
                                groupingBy(
                                        String::length,
                                        counting()));
        System.out.println(shortWordCounts);

        // Downstream order not deterministic
        Map<Integer, List<String>> result = words.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length));

        System.out.println(result.get(14));

        result = words.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length));

        System.out.println(result.get(14));

        Map<Integer, Long> wordCounts =
                words.parallelStream()
                        .collect(
                                groupingByConcurrent(
                                        String::length,
                                        counting()));

        System.out.println(wordCounts);
    }
}
