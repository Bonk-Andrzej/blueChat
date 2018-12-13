package com.wildBirds.BlueChat.api.webSocket.types;

public enum RemoteProcedure{
    ADDMESSAGE, ERROR, ADDMYMESSAGE;

    @Override
    public String toString() {
        return this.name();
    }
}
