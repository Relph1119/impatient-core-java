package sec01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CallableDemo {
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

    public static Set<Path> descendants(Path p) throws IOException {
        try (Stream<Path> entries = Files.walk(p)) {
            return entries.collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        String word = "String";
        Set<Path> paths = descendants(Path.of("."));
        var tasks = new ArrayList<Callable<Long>>();
        for (Path p : paths)
            tasks.add(
                    () -> occurrences(word, p));
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        // 提交所有子任务
        List<Future<Long>> results = executor.invokeAll(tasks);
        long total = 0;
        // 在所有子任务完成之前都会阻塞，直到结果可用
        for (Future<Long> result : results) total += result.get();
        System.out.println("Occurrences of String: " + total);

        String searchWord = "occurrences";
        var searchTasks = new ArrayList<Callable<Path>>();
        for (Path p : paths)
            searchTasks.add(
                    () -> {
                        if (occurrences(searchWord, p) > 0) return p;
                        else throw new RuntimeException();
                    });
        // 在提交的任何一个任务正常完成后就会返回
        Path found = executor.invokeAny(searchTasks);
        System.out.println(found);
        executor.shutdown();
    }
}
