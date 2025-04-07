package com.service1.orchestrator.exception;

public class ErrorResponse {
    private String status;
    private String message;

    // Constructors, getters, setters
    public ErrorResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters and setters
    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public void setStatus(String status) { this.status = status; }
    public void setMessage(String message) { this.message = message; }
}
