package com.wildBirds.BlueChat.domain.model.exceptions;

public class ContactMessageException extends RuntimeException {
    public ContactMessageException(String message) {
        super(message);
    }
}
