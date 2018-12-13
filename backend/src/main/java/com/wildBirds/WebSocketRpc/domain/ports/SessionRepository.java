package com.wildBirds.WebSocketRpc.domain.ports;

import com.wildBirds.WebSocketRpc.domain.model.Session;
import org.springframework.web.socket.WebSocketSession;

/**
 *
 * @param <RT> Remote type
 * @param <I> ID type
 */
public interface SessionRepository<RT extends Enum<RT>,I> {

    void addSession(Session<RT, I> session);

    Session<RT,I> getSession(I Id);

    Session<RT,I> getSession(WebSocketSession session);

    void removeSession(WebSocketSession session);
}
