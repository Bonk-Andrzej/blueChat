package com.wildBirds.WebSocketRpc.infrastructure;

import com.wildBirds.WebSocketRpc.domain.model.Session;
import com.wildBirds.WebSocketRpc.domain.ports.SessionNotExist;
import com.wildBirds.WebSocketRpc.domain.ports.SessionRepository;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.TreeMap;

public class SessionRepositoryInMemory<RT extends Enum<RT>, I extends Comparable<I>> implements SessionRepository<RT, I> {

    private Map<I, Session<RT, I>> authorizedSessionMap;
    private Map<String, Session<RT, I>> allSessionMap;

    public SessionRepositoryInMemory() {
        authorizedSessionMap = new TreeMap<>();
        allSessionMap = new TreeMap<>();
    }

    public Map<I, Session<RT, I>> getAuthorizedSessionMap() {
        return authorizedSessionMap;
    }

    public Map<String, Session<RT, I>> getAllSessionMap() {
        return allSessionMap;
    }

    @Override
    public void addSession(Session<RT, I> session) {


        if (session.hasId()) {
            authorizedSessionMap.put(session.getId(), session);
            allSessionMap.put(session.getWebSocketSession().getId(), session);
        } else {
            allSessionMap.put(session.getWebSocketSession().getId(), session);
        }
    }


    @Override
    public Session<RT, I> getSession(I id) {
        if (authorizedSessionMap.containsKey(id)) {
            return authorizedSessionMap.get(id);
        } else {
            throw new SessionNotExist("Session no exist in Repository");
        }
    }

    @Override
    public Session<RT, I> getSession(WebSocketSession webSocketSession) {
        if (allSessionMap.containsKey(webSocketSession.getId())) {
            return allSessionMap.get(webSocketSession.getId());
        } else {
            throw new SessionNotExist("Session no exist in Repository");
        }
    }

    @Override
    public void removeSession(WebSocketSession webSocketSession) {
        Session<RT, I> session = allSessionMap.get(webSocketSession.getId());
        if (session.hasId()) {
            authorizedSessionMap.remove(session.getId());
        }
        allSessionMap.remove(webSocketSession.getId());
    }
}
