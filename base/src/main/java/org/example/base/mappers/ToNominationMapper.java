package org.example.base.mappers;

import org.example.base.dto.NominationDto;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class ToNominationMapper {

    public static NominationDto mapToNominationDto(Object rawData) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = (JsonNode) rawData;

        // Nominatim возвращает МАССИВ результатов, берём первый элемент
        if (jsonNode.isArray() && !jsonNode.isEmpty()) {
            JsonNode firstResult = jsonNode.get(0);

            return objectMapper.convertValue(firstResult, NominationDto.class);
        }

        throw new RuntimeException("No geocoding results found");
    }
}
