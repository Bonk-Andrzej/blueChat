package com.wildBirds.WebSocketRpc.application.services.eventEmmiter;

@FunctionalInterface
public interface EventEmitterHandler<TYPE> {

    void execute(TYPE data);
}
