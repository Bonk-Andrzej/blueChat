package com.wildBirds.BlueChat.api.webSocket.dto;

public class AuthSessionDTO {
    private Long userId;

    public AuthSessionDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
