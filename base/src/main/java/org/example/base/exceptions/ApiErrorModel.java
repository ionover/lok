package org.example.base.exceptions;

import org.springframework.http.HttpStatus;

public class ApiErrorModel {
    private HttpStatus status;
    private String message;

    public ApiErrorModel() {
    }

    public ApiErrorModel(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
