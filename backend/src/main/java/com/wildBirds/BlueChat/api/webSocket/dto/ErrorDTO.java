package com.wildBirds.BlueChat.api.webSocket.dto;

public class ErrorDTO {
    private String message;
    private String status;

    public ErrorDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
