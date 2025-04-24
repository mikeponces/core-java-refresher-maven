package aero.champ.training.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example {
    public static void main(String[] args) {

    }

    private void initExamples() {
        Stream<String> streamValues = Stream.of("a", "b", "c");

        Stream.generate(Math::random).limit(5);

        Stream.iterate(0, n-> n+2).limit(5);

        IntStream arrayStream = Arrays.stream(new int[]{1, 2, 3, 4, 5});

        List<String> programmingLanguages = Arrays.asList("Java", "C#", "Python");
        programmingLanguages.stream();

        Path filePath = Path.of("sample.txt");
        try(Stream<String> fileStream = Files.lines(filePath)) {
            fileStream.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void intermediateOperators() {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

        List<Integer> processedNumbers = numbers
                .distinct()
                .filter(number -> number > 3)
                .limit(8)
                .map(number -> number * 2)
                .peek(System.out::println) // for debugging purposes
                .skip(1)
                .sorted()
                .toList(); // terminal

        System.out.println(processedNumbers);
    }

    private void terminalOperators() {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

        numbers.forEach(System.out::println);

        numbers.count();

        numbers.toList(); // before Java 16: collect(Collectors.toList())

        numbers.reduce(0, (a, b) -> a + b);

        numbers.findFirst();

        numbers.findAny();

        numbers.allMatch(number -> number > 3);

        numbers.anyMatch(number -> number > 3);

        numbers.noneMatch(number -> number > 3);

        numbers.min(Integer::compare);

        numbers.max(Integer::compare);
    }
}
