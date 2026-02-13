package org.example.base.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.base.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WebSearcher {

    private final String BASE_OSM_URL = "https://nominatim.openstreetmap.org";

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public WebSearcher(OkHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public Object searchByAddress(JsonNode data) {
        String address = data.toString();
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String url = BASE_OSM_URL + "/search?q=" + encodedAddress + "&format=json";

        return doRequest(url);
    }

    public Object searchByCoordinates(JsonNode data) {
        JsonNode xNode = data.get("x");
        JsonNode yNode = data.get("y");

        if (xNode == null || yNode == null || xNode.isNull() || yNode.isNull()) {
            throw new IllegalArgumentException("Для поиска по координатам нужны поля 'x' и 'y'." +
                                                       " Пример: {\"x\":\"50.60\",\"y\":\"60.50\"}");
        }

        String x = xNode.asString();
        String y = yNode.asString();

        String encodedLon = URLEncoder.encode(x, StandardCharsets.UTF_8);
        String encodedLat = URLEncoder.encode(y, StandardCharsets.UTF_8);

        String url = BASE_OSM_URL + "/reverse"
                + "?lat=" + encodedLat
                + "&lon=" + encodedLon
                + "&format=json"
                + "&addressdetails=1";

        return doRequest(url);
    }

    private Object doRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "GeoCoding-Service/1.0")
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();

                return objectMapper.readTree(responseBody);
            }

            throw new NotFoundException(
                    "OSM вернул неуспешный ответ, либо ответ с пустым телом. Подробнее => " + response);
        } catch (Exception e) {
            throw new NotFoundException("Ошибка во время выполнения запроса на openstreetmap: " + e.getMessage());
        }
    }
}
