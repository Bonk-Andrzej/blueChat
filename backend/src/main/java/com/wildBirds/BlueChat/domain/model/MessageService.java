package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;


public class MessageService {


    @Autowired
    private MessageRepository msgRepo;

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public Message toEntity(MessageDto messageDto) {
        Message message = new Message();

        if (messageDto.getIdMessage() != null) {
            message = msgRepo.getOne(messageDto.getIdMessage());
        }else {
            message = msgRepo.save(message);
        }

        message.setContent(messageDto.getContent());
        message.setReceiver(userRepo.getOne(messageDto.getReceiverId()));
        message.setSender(userRepo.getOne(messageDto.getSenderId()));
        message.setSentDate(messageDto.getSentDate());

        return message;
    }

    public MessageDto toDto (Message message){
        MessageDto messageDto = new MessageDto();

        messageDto.setIdMessage(message.getIdMessage());
        messageDto.setSentDate(message.getSentDate());
        messageDto.setContent(message.getContent());
        messageDto.setSenderId(message.getSender().getIdUser());
        messageDto.setReceiverId(message.getReceiver().getIdUser());
        messageDto.setSentDate(message.getSentDate());
        return messageDto;
    }
}
