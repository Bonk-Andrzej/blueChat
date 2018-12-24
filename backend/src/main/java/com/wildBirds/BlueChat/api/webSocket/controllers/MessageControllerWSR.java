package com.wildBirds.BlueChat.api.webSocket.controllers;


import com.wildBirds.BlueChat.api.rest.controllers.ChannelsMessageController;
import com.wildBirds.BlueChat.api.rest.dto.ChannelsMessageDto;
import com.wildBirds.BlueChat.api.rest.dto.MessageDto;
import com.wildBirds.BlueChat.api.webSocket.dto.AuthSessionDTO;
import com.wildBirds.BlueChat.api.webSocket.dto.ErrorDTO;
import com.wildBirds.BlueChat.api.webSocket.dto.MessageDTO;
import com.wildBirds.BlueChat.api.webSocket.types.LocalProcedure;
import com.wildBirds.BlueChat.api.webSocket.types.RemoteProcedure;
import com.wildBirds.BlueChat.domain.model.ChannelsMessageFacade;
import com.wildBirds.BlueChat.domain.model.MessageFacade;
import com.wildBirds.WebSocketRpc.api.Session;
import com.wildBirds.WebSocketRpc.api.WSR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Component
public class MessageControllerWSR implements InitializingBean {

    private WSR<LocalProcedure, RemoteProcedure, Long> wsr;
    private MessageFacade messageFacade;
    private ChannelsMessageFacade channelsMessageFacade;
    private Logger log = LoggerFactory.getLogger(ChannelsMessageController.class);

    @Autowired
    public MessageControllerWSR(WSR<LocalProcedure, RemoteProcedure, Long> wsr, MessageFacade messageFacade, ChannelsMessageFacade channelsMessageFacade) {
        this.wsr = wsr;
        this.messageFacade = messageFacade;
        this.channelsMessageFacade = channelsMessageFacade;
    }

    @Override
    public void afterPropertiesSet() {

        log.info(" WSR Message controller start");
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

            log.info("Authorized User by id: " + data.getUserId());
            final ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setMessage("Authorized succeed");
            errorDTO.setStatus("OK");
            session.executeRemoteProcedure(RemoteProcedure.ERROR, ErrorDTO.class, errorDTO);

        });
        wsr.addProcedure(LocalProcedure.FORWARDCHANNELSMESSAGE, ChannelsMessageDto.class, ((data, session) -> {
            ChannelsMessageDto channelsMessageDto = new ChannelsMessageDto();

            channelsMessageDto.setSenderId(data.getSenderId());
            channelsMessageDto.setChannelId(data.getChannelId());
            channelsMessageDto.setSentDate(Instant.now());
            channelsMessageDto.setContent(data.getContent());

            channelsMessageFacade.saveMessage(channelsMessageDto);

            session.executeRemoteProcedure(RemoteProcedure.ADDMYCHANNELMESSAGE, ChannelsMessageDto.class, channelsMessageDto);

//            List<Session<RemoteProcedure, Long>> allAuthorizedSession = wsr.getAllAuthorizedSession();
//            allAuthorizedSession.forEach(session.executeRemoteProcedure
//                    (RemoteProcedure.REFRESHCHANNELMESSAGES, ChannelsMessageDto.class, channelsMessageDto));

        }));

    }

    public List<Long> getAuthorizatedSessionsIdentificators(){
        return this.wsr.getAuthorizatedSessionsIdentificators();
    }
}
