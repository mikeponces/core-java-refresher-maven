package aero.champ.exercise.virtualthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class VirtualThreadClient {
    private final ApiService apiService;

    public VirtualThreadClient(ApiService apiService) {
        this.apiService = apiService;
    }

    public List<String> fetchAllDataWithVirtualThreads(int count) throws
            InterruptedException {
// Use the virtual threads executor
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<String>> futures = new ArrayList<>();
            List<String> results = new ArrayList<>();
// Submit tasks to the executor
            for (int i = 1; i <= count; i++) {
                final int resourceId = i;
                futures.add(executor.submit(() -> {
                    try {
                        System.out.println("Virtual thread " +
                                Thread.currentThread().threadId() +
                                " fetching resource " + resourceId);
                        return apiService.fetchData(resourceId);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "Error fetching resource " + resourceId + ": " +
                                e.getMessage();
                    }
                }));
            }
// Collect results
            for (Future<String> future : futures) {
                try {
                    results.add(future.get());
                } catch (ExecutionException e) {
                    results.add("Error: " + e.getCause().getMessage());
                }
            }
            return results;
        }
    }

    // Alternative implementation using structured concurrency
    public List<String> fetchAllDataWithStructuredConcurrency(int count) throws
            Exception {
        List<String> results = new ArrayList<>(count);
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            List<StructuredTaskScope.Subtask<String>> tasks = new ArrayList<>();
// Launch tasks
            for (int i = 1; i <= count; i++) {
                final int resourceId = i;
                tasks.add(scope.fork(() -> {
                    System.out.println("Structured virtual thread " +
                            Thread.currentThread().threadId() +
                            " fetching resource " + resourceId);
                    return apiService.fetchData(resourceId);
                }));
            }
// Wait for all tasks to complete or one to fail
            scope.join();
// Propagate any exceptions
            scope.throwIfFailed();
// Collect results in order
            for (var task : tasks) {
                results.add(task.get());
            }
            return results;
        }
    }
}
