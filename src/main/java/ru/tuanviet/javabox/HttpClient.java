package ru.tuanviet.javabox;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class HttpClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OkHttpClient client;

    public HttpClient() {
        client = new OkHttpClient.Builder().build();
    }

    public <T> T fetch(String uri, Class<T> clazz) {
        try {
            return objectMapper.readValue(fetchBytes(uri), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T fetch(String uri, TypeReference<T> type) {
        try {
            return objectMapper.readValue(fetchBytes(uri), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] fetchBytes(String uri) {
        try {
            Request request = new Request.Builder()
                    .url(uri)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                return requireNonNull(response.body()).bytes();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
