package aero.champ.training.jdk21;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example {
    public static void main(String[] args) {

    }

    private void structuredConcurrency() {
//        try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//            StructuredTaskScope.Subtask<String> apiData = scope.fork(() -> "test");
//            StructuredTaskScope.Subtask<Integer> computedValue = scope.fork(() -> 1);
//            scope.join();
//        }
    }

    private void virtualThreads() {
        List<String> users = List.of("a", "b", "c", "d", "e", "f", "g", "h");
        try(ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (String user : users) {
                executor.submit(() -> {
                    // TODO
                });
            }
        }
    }
}
