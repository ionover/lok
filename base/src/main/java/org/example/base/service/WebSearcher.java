package org.example.base.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.base.dto.NominationDto;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WebSearcher {

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public WebSearcher(OkHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public NominationDto searchByAddress(Object data) {
        String address = data.toString();
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String url = "https://nominatim.openstreetmap.org/search?q=" + encodedAddress + "&format=json";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "GeoCoding-Service/1.0")
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            String responseBody = response.body().string();

            return objectMapper.readTree(responseBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
