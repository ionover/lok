package org.example.base.service.convert;

import tools.jackson.databind.JsonNode;
import org.example.base.enums.Type;

public interface IConverter {
    Type type();
    Object convert(JsonNode data);
}
