package com.wildBirds.WebSocketRpc.domain.model;


import com.wildBirds.WebSocketRpc.api.ProcedureDTO;
import com.wildBirds.WebSocketRpc.application.services.ProcedureDTOConverter;
import com.wildBirds.WebSocketRpc.domain.ports.SessionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.nio.charset.Charset;

public class Session<RT extends Enum<RT>, I> implements com.wildBirds.WebSocketRpc.api.Session<RT, I> {

    private ProcedureDTOConverter<RT> procedureDTOConverter;
    private SessionRepository<RT, I> sessionRepository;
    private WebSocketSession webSocketSession;
    private I id;

    public Session(WebSocketSession webSocketSession, ProcedureDTOConverter procedureDTOConverter, SessionRepository sessionRepository) {
        this.webSocketSession = webSocketSession;
        this.procedureDTOConverter = procedureDTOConverter;
        this.sessionRepository = sessionRepository;
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    @Override
    public I getId() {
        return id;
    }

    @Override
    public void setId(I id) {
        this.id = id;
        this.sessionRepository.addSession(this);
    }

    @Override
    public boolean hasId() {
        return (this.id != null);
    }

    @Override
    public <D> void executeRemoteProcedure(RT procedureType, Class<D> dataType, D data) {

        ProcedureDTO<RT, D> procedureDTO = new ProcedureDTO<>(procedureType, data);
        try {
            String jsonString = procedureDTOConverter.toJsonString(procedureDTO);
            TextMessage textMessage = new TextMessage(jsonString.getBytes(Charset.forName("UTF-8")));
            webSocketSession.sendMessage(textMessage);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
