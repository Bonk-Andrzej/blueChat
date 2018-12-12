package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.dto.MessageDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MessageService {

    private UserService userService;

    public Message toEntity(MessageDto messageDto) {
        Message message = new Message();

        if (messageDto.getIdMessage() != null) {
            message.setIdMessage(messageDto.getIdMessage());
        }
        message.setContent(messageDto.getContent());
        message.setReceiver(userService.toEntity(messageDto.getReceiver()));
        message.setSender(userService.toEntity(messageDto.getSender()));
        message.setSendDate(messageDto.getSendDate());

        return message;
    }

    public MessageDto toDto (Message message){
        MessageDto messageDto = new MessageDto();

        messageDto.setIdMessage(message.getIdMessage());
        messageDto.setSendDate(message.getSendDate());
        messageDto.setSender(userService.toDto(message.getSender()));
        messageDto.setReceiver(userService.toDto(message.getReceiver()));
        messageDto.setSendDate(message.getSendDate());
        return messageDto;
    }
}
