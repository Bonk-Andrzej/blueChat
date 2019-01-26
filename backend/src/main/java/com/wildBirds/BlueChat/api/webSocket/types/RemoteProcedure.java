package com.wildBirds.BlueChat.api.webSocket.types;

public enum RemoteProcedure{
    ADDMESSAGE, ERROR, ADDMYMESSAGE, ADDMYCHANNELMESSAGE, ADDCHANNELMESSAGE, FRIENDJOIN, FRIENDLEAVE, ;

    @Override
    public String toString() {
        return this.name();
    }
}
