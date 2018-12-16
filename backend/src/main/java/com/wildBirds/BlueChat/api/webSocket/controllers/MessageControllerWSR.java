package com.wildBirds.BlueChat.api.webSocket.controllers;


import com.wildBirds.BlueChat.api.rest.dto.MessageDto;
import com.wildBirds.BlueChat.api.webSocket.dto.AuthSessionDTO;
import com.wildBirds.BlueChat.api.webSocket.dto.ErrorDTO;
import com.wildBirds.BlueChat.api.webSocket.dto.MessageDTO;
import com.wildBirds.BlueChat.api.webSocket.types.LocalProcedure;
import com.wildBirds.BlueChat.api.webSocket.types.RemoteProcedure;
import com.wildBirds.BlueChat.domain.model.MessageFacade;
import com.wildBirds.WebSocketRpc.api.Session;
import com.wildBirds.WebSocketRpc.api.WSR;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class MessageControllerWSR implements InitializingBean {

    private WSR<LocalProcedure, RemoteProcedure, Long> wsr;
    private MessageFacade messageFacade;

    @Autowired
    public MessageControllerWSR(WSR<LocalProcedure, RemoteProcedure, Long> wsr, MessageFacade messageFacade) {
        this.wsr = wsr;
        this.messageFacade = messageFacade;

    }

    @Override
    public void afterPropertiesSet() {

        System.out.println(" WSR Message controller start");
        wsr.addProcedure(LocalProcedure.FORWARDMESSAGE, MessageDTO.class, (data, session) -> {

            if (session.hasId()) {

                MessageDto message = new MessageDto();
                message.setContent(data.getContent());
                message.setReceiverId(data.getReceiverId());
                message.setSenderId(session.getId());
                message.setSentDate(Instant.now());


                data.setSentDate(Timestamp.from(message.getSentDate()));

                messageFacade.saveMessage(message);
                session.executeRemoteProcedure(RemoteProcedure.ADDMYMESSAGE, MessageDTO.class, data);
                try {
                    Session<RemoteProcedure, Long> receiverSession = wsr.findSession(data.getReceiverId());
                    receiverSession.executeRemoteProcedure(RemoteProcedure.ADDMESSAGE, MessageDTO.class, data);
                } catch (Exception e) {


                }

            } else {
                ErrorDTO errorDTO = new ErrorDTO();
                errorDTO.setStatus("403");
                errorDTO.setMessage("Unauthorized");
                session.executeRemoteProcedure(RemoteProcedure.ERROR, ErrorDTO.class, errorDTO);
            }


        });
        wsr.addProcedure(LocalProcedure.AUTHSESSION, AuthSessionDTO.class, (data, session) -> {

            Long userId = data.getUserId();
            session.setId(userId);

            System.out.println("Authorized User by id: " + data.getUserId());
            final ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setMessage("Authorized succeed");
            errorDTO.setStatus("OK");
            session.executeRemoteProcedure(RemoteProcedure.ERROR, ErrorDTO.class, errorDTO);

        });

    }
}
