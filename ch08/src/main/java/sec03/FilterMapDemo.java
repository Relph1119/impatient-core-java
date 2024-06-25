package sec03;

import sec01.CountLongWords;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterMapDemo {
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

    public static Stream<String> codePoints(String s) {
        return s.codePoints().mapToObj(cp -> new String(new int[]{cp}, 0, 1));
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path path = Paths.get(FilterMapDemo.class.getResource("../alice.txt").toURI());
        String contents = Files.readString(path);
        List<String> words = List.of(contents.split("\\PL+"));
        Stream<String> longWords = words.stream().filter(w -> w.length() > 12);
        show("longWords", longWords);

        Stream<String> lowercaseWords = words.stream().map(String::toLowerCase);
        show("lowercaseWords", lowercaseWords);

        String[] song = {"row", "row", "row", "your", "boat", "gently", "down",
                "the", "stream"};
        Stream<String> firstChars = Stream.of(song).filter(w -> w.length() > 0).map(s -> s.substring(0, 1));
        show("firstChars", firstChars);

        // 使用flatMap方法进行平摊为单一流形式
        Stream<String> letters = Stream.of(song).flatMap(w -> codePoints(w));
        show("letters", letters);

        Stream<String> result = words.stream().mapMulti((s, collector) -> {
            int i = 0;
            while (i < s.length()) {
                int cp = s.codePointAt(i);
                // 将结果传递给收集器
                collector.accept(new String(new int[]{cp}, 0, 1));
                i += Character.charCount(cp);
            }
        });
        show("result", result);
    }
}
