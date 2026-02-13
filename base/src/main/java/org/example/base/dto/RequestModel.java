package org.example.base.dto;

import tools.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;
import org.example.base.enums.Type;

public class RequestModel {

    @NotNull
    private Type type;
    @NotNull
    private JsonNode payload;

    public RequestModel() {
    }

    public RequestModel(Type type, JsonNode payload) {
        this.type = type;
        this.payload = payload;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public JsonNode getPayload() {
        return payload;
    }

    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "type=" + type +
                ", request=" + payload +
                '}';
    }
}
