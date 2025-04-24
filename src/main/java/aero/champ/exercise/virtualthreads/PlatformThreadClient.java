package aero.champ.exercise.virtualthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PlatformThreadClient {
    private final ApiService apiService;

    public PlatformThreadClient(ApiService apiService) {
        this.apiService = apiService;
    }

    public List<String> fetchAllDataWithPlatformThreads(int count) throws
            InterruptedException {
// Create a thread pool with a limited number of threads
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<Future<String>> futures = new ArrayList<>();
        List<String> results = new ArrayList<>();
// Submit tasks to the executor
        for (int i = 1; i <= count; i++) {
            final int resourceId = i;
            futures.add(executorService.submit(() -> {
                try {
                    System.out.println("Platform thread " +
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
// Shutdown the executor
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        return results;
    }
}
