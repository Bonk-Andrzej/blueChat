package com.wildBirds.BlueChat.domain.model.exceptions;

public class ChannelNoxExistException extends RuntimeException {
    public ChannelNoxExistException(String message) {
        super(message);
    }
}
