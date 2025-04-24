package aero.champ.exercise.virtualthreads;

import java.util.List;

public class VirtualThreadsDemo {
    private static final int NUM_REQUESTS = 500;

    public static void main(String[] args) throws Exception {
        ApiService apiService = new ApiService();
        PlatformThreadClient platformClient = new PlatformThreadClient(apiService);
        VirtualThreadClient virtualClient = new VirtualThreadClient(apiService);
// Measure platform threads performance
        System.out.println("Starting Platform Threads demo with " + NUM_REQUESTS + "concurrent requests...");
        long platformStart = System.currentTimeMillis();
        List<String> platformResults =
                platformClient.fetchAllDataWithPlatformThreads(NUM_REQUESTS);
        long platformEnd = System.currentTimeMillis();
        System.out.println("Platform Threads completed in " + (platformEnd -
                platformStart) + "ms");
        System.out.println("Retrieved " + platformResults.size() + " results\n");
// Give some time between tests
        Thread.sleep(1000);
// Measure virtual threads performance
        System.out.println("Starting Virtual Threads demo with " + NUM_REQUESTS + "concurrent requests...");
        long virtualStart = System.currentTimeMillis();
        List<String> virtualResults =
                virtualClient.fetchAllDataWithVirtualThreads(NUM_REQUESTS);
        long virtualEnd = System.currentTimeMillis();
        System.out.println("Virtual Threads completed in " + (virtualEnd -
                virtualStart) + "ms");
        System.out.println("Retrieved " + virtualResults.size() + " results\n");
// Try with structured concurrency if desired
        System.out.println("Starting Structured Concurrency demo with " + NUM_REQUESTS
                + " concurrent requests...");
        long structuredStart = System.currentTimeMillis();
        List<String> structuredResults =
                virtualClient.fetchAllDataWithStructuredConcurrency(NUM_REQUESTS);
        long structuredEnd = System.currentTimeMillis();
        System.out.println("Structured Concurrency completed in " + (structuredEnd -
                structuredStart) + "ms");
        System.out.println("Retrieved " + structuredResults.size() + " results");
    }
}
