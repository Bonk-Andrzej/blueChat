package com.wildBirds.BlueChat.api.webSocket.types;

public enum RemoteProcedure{
    ADDMESSAGE, ERROR, ADDMYMESSAGE, ADDMYCHANNELMESSAGE, REFRESHCHANNELMESSAGES;

    @Override
    public String toString() {
        return this.name();
    }
}
