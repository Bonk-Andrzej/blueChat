package com.wildBirds.BlueChat.api.webSocket.types;

public enum LocalProcedure {
    FORWARDMESSAGE,  AUTHSESSION, FORWARDCHANNELMESSAGE;

    @Override
    public String toString() {
        return this.name();
    }
}
