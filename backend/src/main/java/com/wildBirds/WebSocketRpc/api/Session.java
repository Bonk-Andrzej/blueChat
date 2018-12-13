package com.wildBirds.WebSocketRpc.api;

import org.springframework.web.socket.WebSocketSession;

public interface Session<RT, I> {

    I getId();
    void setId(I id);
    boolean hasId();
    <D> void executeRemoteProcedure(RT procedureType, Class<D> dataType, D data);
    WebSocketSession getWebSocketSession();
}
