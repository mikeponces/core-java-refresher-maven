package aero.champ.exercise.virtualthreads;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class ApiService {
    private final OkHttpClient client;
    private final Random random = new Random();
    // For demo purposes, we're using a public mock API
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public ApiService() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(30))
                .build();
    }

    public String fetchData(int resourceId) throws IOException, InterruptedException {
// Simulate variable network latency (200-700ms)
        Thread.sleep(200 + random.nextInt(500));
        String url = BASE_URL + "/posts/" + resourceId;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }
            return response.body().string();
        }
    }
}
