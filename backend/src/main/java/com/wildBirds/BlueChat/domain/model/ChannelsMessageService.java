package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.ChannelsMessageDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChannelsMessageService {
    private ChannelService channelService;
    private UserService userService;


    public ChannelsMessage toEntity(ChannelsMessageDto channelsMessageDto) {
        ChannelsMessage channelsMessage = new ChannelsMessage();

        if (channelsMessageDto.getIdMessageGroup() == null) {
            channelsMessage.setIdMessageGroup(channelsMessageDto.getIdMessageGroup());
        }
        channelsMessage.setSentDate(channelsMessageDto.getSentDate());
        channelsMessage.setSender(userService.toEntity(channelsMessageDto.getSender()));
        channelsMessage.setContent(channelsMessageDto.getContent());
        channelsMessage.setChannel(channelService.toEntity(channelsMessageDto.getChannel()));
        return channelsMessage;
    }
    public ChannelsMessageDto toDto(ChannelsMessage channelsMessage){
        ChannelsMessageDto channelsMessageDto = new ChannelsMessageDto();

        channelsMessageDto.setIdMessageGroup(channelsMessage.getIdMessageGroup());
        channelsMessageDto.setChannel(channelService.toDto(channelsMessage.getChannel()));
        channelsMessageDto.setSender(userService.toDto(channelsMessage.getSender()));
        channelsMessageDto.setContent(channelsMessage.getContent());
        channelsMessageDto.setSentDate(channelsMessage.getSentDate());
        return channelsMessageDto;
    }
}
