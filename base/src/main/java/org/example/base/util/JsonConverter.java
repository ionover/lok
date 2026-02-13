package org.example.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class JsonConverter {

    private static final Logger log = LoggerFactory.getLogger(JsonConverter.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode toJsonNode(Object coordinates) {

        try {
            if (coordinates instanceof String) {
                return mapper.readTree((String) coordinates);
            }

            return mapper.valueToTree(coordinates);
        } catch (Exception e) {
            log.error("Failed to convert to JsonNode: {}", coordinates, e);

            throw new IllegalArgumentException("Ошибка конвертации в JSON: " + e.getMessage());
        }
    }
}
