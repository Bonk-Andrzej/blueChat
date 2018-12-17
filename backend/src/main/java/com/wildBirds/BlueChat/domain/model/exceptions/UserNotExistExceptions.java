package com.wildBirds.BlueChat.domain.model.exceptions;

public class UserNotExistExceptions extends RuntimeException{
    public UserNotExistExceptions(String message) {
        super(message);
    }
}
