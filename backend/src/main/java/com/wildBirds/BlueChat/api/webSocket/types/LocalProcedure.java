package com.wildBirds.BlueChat.api.webSocket.types;

public enum LocalProcedure {
    FORWARDMESSAGE,  AUTHSESSION, FORWARDCHANNELSMESSAGE;

    @Override
    public String toString() {
        return this.name();
    }
}
