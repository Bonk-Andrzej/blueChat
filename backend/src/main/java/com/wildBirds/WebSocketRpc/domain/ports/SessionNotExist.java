package com.wildBirds.WebSocketRpc.domain.ports;

public class SessionNotExist extends RuntimeException {
    public SessionNotExist(String message) {
        super(message);
    }
}
