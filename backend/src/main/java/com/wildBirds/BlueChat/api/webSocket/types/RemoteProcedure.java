package com.wildBirds.BlueChat.api.webSocket.types;

public enum RemoteProcedure{
    ADDMESSAGE, ERROR, ADDMYMESSAGE, ADDMYCHANNELMESSAGE, REFRESHCHANNELMESSAGES, FRIENDJOIN, FRIENDLEAVE, ;

    @Override
    public String toString() {
        return this.name();
    }
}
