
package com.bankflow.bankflow.dto;

import java.time.LocalDateTime;

public class ApiError {

    private String message;
    private LocalDateTime timestamp;

    public ApiError(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
