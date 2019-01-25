package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.UserDtoWithLastMessage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class UserWithLasMessageFacade {

    private MessageService messageService;
    private MessageFacade messageFacade;
    private PhotoService photoService;

    public UserWithLasMessageFacade(MessageService messageService,
                                    MessageFacade messageFacade,
                                    PhotoService photoService) {
        this.messageService = messageService;
        this.messageFacade = messageFacade;
        this.photoService = photoService;
    }

    public List<UserDtoWithLastMessage> getFirstNoReadMessages(Long idUser){
        List<Message> noReadMessages = messageFacade.getNoReadMessages(idUser);

        Set<Long> setHelper = new HashSet<>();

        noReadMessages.removeIf(message -> !setHelper.add(message.getSender().getIdUser()));

        List<UserDtoWithLastMessage> result = new ArrayList<>();

        for (Message noReadMessage : noReadMessages) {
            UserDtoWithLastMessage userDto = new UserDtoWithLastMessage();
            userDto.setIdUser(noReadMessage.getSender().getIdUser());
            userDto.setLastMessage(messageService.toDto(noReadMessage));
            userDto.setNick(noReadMessage.getSender().getNick());
            userDto.setPhotoDto(photoService.toDto(noReadMessage.getSender().getProfilePhoto()));
            result.add(userDto);
        }
        return result;
    }
}
