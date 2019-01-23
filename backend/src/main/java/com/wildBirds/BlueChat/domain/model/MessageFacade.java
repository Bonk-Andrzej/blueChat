package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.MessageDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MessageFacade {
    private MessageRepository messageRepository;
    private MessageService messageService;



    public MessageDto saveMessage(MessageDto messageDto) {
        Message message = messageService.toEntity(messageDto);


        Message savedMessage = messageRepository.saveMessage(message);
        MessageDto response = messageService.toDto(savedMessage);
        return response;
    }

    public List<MessageDto> getConversation(Long sender, Long receiver, Integer limit, Integer toBound) {

        List<Message> conversation = messageRepository.getConversation(sender, receiver, limit, toBound);

        List<MessageDto> conversationDto = conversation.stream()
                .map(message -> messageService.toDto(message))
                .collect(Collectors.toList());


        return conversationDto;
    }

    public List<MessageDto> getNoReadMessages(Long idUser){
        List<Message> noReadMessages = messageRepository.getNoReadMessages(idUser);

        return noReadMessages.stream()
                .map(message -> messageService.toDto(message))
                .collect(Collectors.toList());
    }
}
