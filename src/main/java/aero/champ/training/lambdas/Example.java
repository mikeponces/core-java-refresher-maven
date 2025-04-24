package aero.champ.training.lambdas;

import java.util.List;

public class Example {
    public static void main(String[] args) {
        // TODO
    }

    private void threadImplementation() {
        Runnable task = () -> System.out.println("Hello from a lambda runnable");
        new Thread(task).start();

        Runnable taskOld = new Runnable() {
            public void run() {
                System.out.println("Hello from an anonymous class");
            }
        };
        new Thread(taskOld).start();
    }

    private void customFunctionalInterface() {
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;

        System.out.println("Adding 10 + 5" + add.calculate(5, 10));
        System.out.println("Multiplying 10 * 5" + multiply.calculate(5, 10));
    }

    private void streamImplementation() {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");
        List<String> filteredNames = names.stream()
                .filter(n -> n.length() > 3)
                .map(String::toUpperCase)
                .sorted()
                .toList();

        System.out.println(filteredNames);
    }
}
