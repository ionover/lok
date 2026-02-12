package org.example.base.dto;

import jakarta.validation.constraints.NotNull;
import org.example.base.enums.Type;

public class RequestModel {

    @NotNull
    private Type type;
    @NotNull
    private Object request;

    public RequestModel() {
    }

    public RequestModel(Type type, Object request) {
        this.type = type;
        this.request = request;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "type=" + type +
                ", request=" + request +
                '}';
    }
}
