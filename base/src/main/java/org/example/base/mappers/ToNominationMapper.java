package org.example.base.mappers;

import org.example.base.dto.NominationDto;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class ToNominationMapper {

    public static NominationDto mapToNominationDto(Object rawData) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = (JsonNode) rawData;

        if (jsonNode.isArray() && !jsonNode.isEmpty()) {
            JsonNode firstResult = jsonNode.get(0);

            return objectMapper.convertValue(firstResult, NominationDto.class);
        }

        if (jsonNode.isObject()) {
            return objectMapper.convertValue(jsonNode, NominationDto.class);
        }

        throw new IllegalArgumentException("Объект [" + rawData + "] не удалось превратить в NominationDto");
    }
}
