package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.MessageDto;
import com.wildBirds.BlueChat.domain.model.exceptions.MessageServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class MessageService {

    private Logger log = LoggerFactory.getLogger(MessageService.class);

    private UserService userService;

    public MessageService(UserService userService) {
        this.userService = userService;
    }

    public Message toEntity(MessageDto messageDto) {
        Message message = null;
        try {
            message = new Message();

            if (messageDto.getIdMessage() != null) {
                message.setIdMessage(messageDto.getIdMessage());
            }

            User sender = new User();
            sender.setIdUser(messageDto.getSender().getIdUser());

            User receiver = new User();
            receiver.setIdUser(messageDto.getReceiver().getIdUser());

            message.setContent(messageDto.getContent());
            message.setReceiver(receiver);
            message.setSender(sender);
            message.setSentDate(messageDto.getSentDate());
        } catch (NullPointerException e) {
            log.error("Message Service - Some field was null", e.getMessage());
            throw new MessageServiceException("Message Service - Some field was null");

        } catch (Exception e) {
            log.error("Message Service Exception", e.getMessage());
            throw new MessageServiceException("Message Service Exception");
        }

        return message;
    }

    public MessageDto toDto (Message message){
        MessageDto messageDto = new MessageDto();

        try {
            messageDto.setIdMessage(message.getIdMessage());

            messageDto.setSentDate(message.getSentDate());

            messageDto.setContent(message.getContent());

            messageDto.setSender(userService.toDtoShort(message.getSender()));

            messageDto.setReceiver(userService.toDtoShort(message.getReceiver()));

            messageDto.setSentDate(message.getSentDate());
        } catch (NullPointerException e) {
            log.error("Message Service - Some field was null", e.getMessage());
            throw new MessageServiceException("Message Service - Some field was null");

        } catch (Exception e) {
            log.error("Message Service Exception", e.getMessage());
            throw new MessageServiceException("Message Service Exception");
        }
        return messageDto;
    }
}
