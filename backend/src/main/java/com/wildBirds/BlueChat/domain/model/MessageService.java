package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.MessageDto;


class MessageService {

    public Message toEntity(MessageDto messageDto) {
        Message message = new Message();

        if (messageDto.getIdMessage() != null) {
            message.setIdMessage(messageDto.getIdMessage());
        }

        User sender = new User();
        sender.setIdUser(messageDto.getSenderId());

        User receiver = new User();
        receiver.setIdUser(messageDto.getReceiverId());

        message.setContent(messageDto.getContent());
        message.setReceiver(receiver);
        message.setSender(sender);
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
