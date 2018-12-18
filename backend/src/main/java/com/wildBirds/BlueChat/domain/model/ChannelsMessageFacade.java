package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.ChannelsMessageDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ChannelsMessageFacade {

    private ChannelsMessageRepository repository;
    private ChannelsMessageService service;

    public ChannelsMessageDto addMessages(ChannelsMessageDto channelsMessageDto){

        ChannelsMessage channelsMessage = service.toEntity(channelsMessageDto);

        ChannelsMessage addedMessage = repository.saveMessage(channelsMessage);

        return service.toDto(addedMessage);

    }
    public List<ChannelsMessageDto> getConversation(Long idChannel, Integer limit, Integer toBound){

        List<ChannelsMessage> conversation = repository.getConversation(idChannel, limit, toBound);

        List<ChannelsMessageDto> messageDtos = conversation.stream()
                .map(channelsMessage -> service.toDto(channelsMessage))
                .collect(Collectors.toList());
        return messageDtos;
    }
}
