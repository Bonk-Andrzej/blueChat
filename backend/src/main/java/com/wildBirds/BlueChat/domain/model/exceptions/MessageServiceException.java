package com.wildBirds.BlueChat.domain.model.exceptions;

public class MessageServiceException extends RuntimeException {
    public MessageServiceException(String message) {
        super(message);
    }
}
