package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.ChannelDto;
import lombok.AllArgsConstructor;


@AllArgsConstructor
class ChannelService {

     private UserService userService;


    protected Channel toEntity(ChannelDto channelDto){
        Channel channel = new Channel();

        if(channelDto.getIdChanel() == null){
            channel.setIdChanel(channelDto.getIdChanel());
        }
        channel.setName(channelDto.getName());
        channel.setPublic(channelDto.getIsPublic());
        channel.setChannelOwner(userService.toEntity(channelDto.getChannelOwner()));

        return channel;
    }
    protected ChannelDto toDto(Channel channel){
        ChannelDto channelDto = new ChannelDto();

        channelDto.setIdChanel(channel.getIdChanel());
        channelDto.setName(channel.getName());
        channelDto.setIsPublic(channel.isPublic());

        return channelDto;
    }
}
