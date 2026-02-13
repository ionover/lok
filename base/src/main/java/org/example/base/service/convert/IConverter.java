package org.example.base.service.convert;

import tools.jackson.databind.JsonNode;
import org.example.base.enums.Type;

public interface IConverter {
    Type type();
    JsonNode convert(JsonNode data);
}
