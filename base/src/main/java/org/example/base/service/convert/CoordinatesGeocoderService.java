package org.example.base.service.convert;


import org.example.base.enums.Type;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

@Service
public class CoordinatesGeocoderService implements IConverter {
    @Override public Type type() { return Type.COORDINATES; }

    @Override public Object convert(JsonNode data) {
        return "converted COORDINATES -> ADDRESS"; // заглушка
    }
}
