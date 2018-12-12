package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.dto.ChannelsMessageDto;

import java.util.List;

public class ChannelsMessageFacade {

    private ChannelsMessageRepository repository;


    public ChannelsMessageDto addMessages(ChannelsMessageDto channelsMessageDto){
//        ChannelsMessage channelsMessage = new ChannelsMessage();
//        if(channelsMessageDto.getIdMessageGroup() == null){
//            channelsMessage.setIdMessageGroup(channelsMessageDto.getIdMessageGroup());
//        };
//
//
//        channelsMessage.setChannel(channelsMessageDto.getChannel());
//
//        repository.save(channelsMessageDto)
        return null;
    }
    public List<ChannelsMessageDto> getConversation(Integer idChannel, Integer limit, Integer toBound){
        return null;
    }
}
