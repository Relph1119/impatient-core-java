package sec08;

import sec01.CountLongWords;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {
    public static Stream<String> noVowels(String filename) throws IOException, URISyntaxException {
        Path path = Paths.get(CollectingResults.class.getResource(String.format("../%s", filename)).toURI());
        String contents = Files.readString(path);
        List<String> wordList = List.of(contents.split("\\PL+"));
        Stream<String> words = wordList.stream();
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    public static <T> void show(String label, Set<T> set) {
        System.out.print(label + ": " + set.getClass().getName());
        System.out.println("["
                + set.stream().limit(10).map(Object::toString).collect(Collectors.joining(", "))
                + "]");
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iter.hasNext())
            System.out.println(iter.next());

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println(Arrays.toString(numbers)); // Note it's an Object[] array

        try {
            var number = (Integer) numbers[0]; // OK
            System.out.println("number: " + number);
            var numbers2 = (Integer[]) numbers; // Throws exception
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }

        Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println(Arrays.toString(numbers3)); // Note it's an Integer[] array

        HashSet<String> noVowelHashSet = noVowels("alice.txt").collect(HashSet::new, HashSet::add,
                HashSet::addAll);
        show("noVowelHashSet", noVowelHashSet);

        Set<String> noVowelSet = noVowels("alice.txt").collect(Collectors.toSet());
        show("noVowelSet", noVowelSet);

        TreeSet<String> noVowelTreeSet = noVowels("alice.txt").collect(
                Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet", noVowelTreeSet);

        String result = noVowels("alice.txt").limit(10).collect(Collectors.joining());
        System.out.println(result);
        result = noVowels("alice.txt").limit(10).collect(Collectors.joining(", "));
        System.out.println(result);

        IntSummaryStatistics summary = noVowels("alice.txt").collect(
                Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();
        System.out.println("Average word length: " + averageWordLength);
        System.out.println("Max word length: " + maxWordLength);

        noVowels("alice.txt").limit(10).forEach(System.out::println);
    }
}
