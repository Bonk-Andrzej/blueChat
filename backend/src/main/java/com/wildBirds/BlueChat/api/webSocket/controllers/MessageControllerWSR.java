package com.wildBirds.BlueChat.api.webSocket.controllers;


import com.wildBirds.BlueChat.api.rest.controllers.ChannelsMessageController;
import com.wildBirds.BlueChat.api.rest.dto.*;
import com.wildBirds.BlueChat.api.webSocket.dto.ErrorDTO;
import com.wildBirds.BlueChat.api.webSocket.types.LocalProcedure;
import com.wildBirds.BlueChat.api.webSocket.types.RemoteProcedure;
import com.wildBirds.BlueChat.domain.model.*;
import com.wildBirds.WebSocketRpc.api.Session;
import com.wildBirds.WebSocketRpc.api.WSR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Component
public class MessageControllerWSR implements InitializingBean {

    private WSR<LocalProcedure, RemoteProcedure, Long> wsr;
    private MessageFacade messageFacade;
    private ChannelsMessageFacade channelsMessageFacade;
    private UserContainFriendFacade userContainFriendFacade;
    private UserFacade userFacade;
    private ChannelFacade channelFacade;
    private Logger log = LoggerFactory.getLogger(ChannelsMessageController.class);

    @Autowired
    public MessageControllerWSR(ChannelFacade channelFacade, @Lazy UserFacade userFacade, @Lazy UserContainFriendFacade userContainFriendFacade, WSR<LocalProcedure, RemoteProcedure, Long> wsr, MessageFacade messageFacade, ChannelsMessageFacade channelsMessageFacade) {
        this.wsr = wsr;
        this.messageFacade = messageFacade;
        this.channelsMessageFacade = channelsMessageFacade;
        this.userContainFriendFacade = userContainFriendFacade;
        this.userFacade = userFacade;
        this.channelFacade = channelFacade;
    }

    @Override
    public void afterPropertiesSet() {

        log.info(" WSR Message controller start");

        wsr.addProcedure(LocalProcedure.FORWARDMESSAGE, MessageDto.class, (data, session) -> {

            if (session.hasId()) {

                data.setSentDate(Instant.now());
                MessageDto messageDto = messageFacade.saveMessage(data);
                session.executeRemoteProcedure(RemoteProcedure.ADDMYMESSAGE, MessageDto.class, messageDto);
                try {
                    Session<RemoteProcedure, Long> receiverSession = wsr.findSession(data.getReceiver().getIdUser());
                    receiverSession.executeRemoteProcedure(RemoteProcedure.ADDMESSAGE, MessageDto.class, data);
                } catch (Exception e) {
                }

            } else {
                ErrorDTO errorDTO = new ErrorDTO();
                errorDTO.setStatus("403");
                errorDTO.setMessage("Unauthorized");
                session.executeRemoteProcedure(RemoteProcedure.ERROR, ErrorDTO.class, errorDTO);
            }


        });

        wsr.addProcedure(LocalProcedure.AUTHSESSION, UserDto.class, (data, session) -> {

            Long userId = data.getIdUser();
            session.setId(userId);

            log.info("Authorized User by id: " + data.getIdUser());
            final ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setMessage("Authorized succeed");
            errorDTO.setStatus("OK");
            session.executeRemoteProcedure(RemoteProcedure.ERROR, ErrorDTO.class, errorDTO);


            Map<Long, com.wildBirds.WebSocketRpc.domain.model.Session<RemoteProcedure, Long>> authSessionIds
                    = this.getAuthorizedSessionsIdentifications();

            List<UserDtoShort> usersFriends = this.userContainFriendFacade.getUsersFriends(userId);
            usersFriends.forEach(userFriends -> {
                Long friendId = userFriends.getIdUser();
                if (authSessionIds.containsKey(friendId)) {
                    com.wildBirds.WebSocketRpc.domain.model.Session<RemoteProcedure, Long> friendSession =
                            authSessionIds.get(friendId);
                    friendSession.executeRemoteProcedure(RemoteProcedure.FRIENDJOIN, UserDto.class, data);
                }
            });
        });

        wsr.addProcedure(LocalProcedure.FORWARDCHANNELMESSAGE, ChannelsMessageDto.class, ((data, session) -> {

            data.setSentDate(Instant.now());
            ChannelsMessageDto channelsMessageDto = channelsMessageFacade.saveMessage(data);

            session.executeRemoteProcedure(RemoteProcedure.ADDMYCHANNELMESSAGE,ChannelsMessageDto.class,channelsMessageDto);

            Map<Long, com.wildBirds.WebSocketRpc.domain.model.Session<RemoteProcedure, Long>> authSessionIds
                    = this.getAuthorizedSessionsIdentifications();
            channelFacade.getChannelsMembers(data.getChannel().getIdChannel()).stream()
                    .forEach(userDtoShort -> {
                        if(authSessionIds.containsKey(userDtoShort.getIdUser()) && userDtoShort.getIdUser() != data.getSender().getIdUser()){
                            authSessionIds.get(userDtoShort.getIdUser()).executeRemoteProcedure(RemoteProcedure.ADDCHANNELMESSAGE,ChannelsMessageDto.class,channelsMessageDto);
                        }
                    });

        }));

        wsr.onCloseConnection.subscribe((session) -> {

            if (session.hasId()) {

                UserDto user = this.userFacade.getById(session.getId());
                Map<Long, com.wildBirds.WebSocketRpc.domain.model.Session<RemoteProcedure, Long>> authSessionId
                        = this.getAuthorizedSessionsIdentifications();
                List<UserDtoShort> usersFriends = this.userContainFriendFacade.getUsersFriends(user.getIdUser());

                usersFriends.forEach(userFriend -> {
                    Long friendId = userFriend.getIdUser();
                    if (authSessionId.containsKey(friendId)) {

                        com.wildBirds.WebSocketRpc.domain.model.Session<RemoteProcedure, Long> friendSession =
                                authSessionId.get(friendId);
                        friendSession.executeRemoteProcedure(RemoteProcedure.FRIENDLEAVE, UserDto.class, user);
                    }
                });
            }
        });

    }

    public Map<Long, com.wildBirds.WebSocketRpc.domain.model.Session<RemoteProcedure, Long>> getAuthorizedSessionsIdentifications() {
        return this.wsr.getAuthorizedSessionsIdentifications();
    }
}
