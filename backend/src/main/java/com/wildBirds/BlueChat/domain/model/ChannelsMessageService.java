package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.ChannelsMessageDto;
import com.wildBirds.BlueChat.domain.model.exceptions.ChannelServiceExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelsMessageService {
    private ChannelService channelService;
    private UserService userService;
    private Logger log = LoggerFactory.getLogger(ChannelsMessageService.class);

    public ChannelsMessageService(ChannelService channelService, UserService userService) {
        this.channelService = channelService;
        this.userService = userService;
    }

    public ChannelsMessage toEntity(ChannelsMessageDto channelsMessageDto) {
        ChannelsMessage channelsMessage = new ChannelsMessage();

        if (channelsMessageDto.getIdChannelsMessageDto() == null) {
            channelsMessage.setIdChannelsMessage(channelsMessageDto.getIdChannelsMessageDto());
        }
        try {
            channelsMessage.setSentDate(channelsMessageDto.getSentDate());
            User senderDto = new User();
            senderDto.setIdUser(channelsMessageDto.getSenderId());

            channelsMessage.setSender(senderDto);
            channelsMessage.setContent(channelsMessageDto.getContent());

            Channel channelDto = new Channel();
            channelDto.setIdChannel(channelsMessageDto.getChannelId());
            channelsMessage.setChannel(channelDto);

        } catch (NullPointerException e) {
            e.printStackTrace();
            log.error("ChannelsMessage Service - Some field was null", e.getMessage());
            throw new ChannelServiceExceptions("Message Service - Some field was null");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("ChannelsMessage Service Exception", e.getMessage());
            throw new ChannelServiceExceptions("Message Service Exception");
        }

        return channelsMessage;
    }
    public ChannelsMessageDto toDto(ChannelsMessage channelsMessage){
        ChannelsMessageDto channelsMessageDto = new ChannelsMessageDto();

        try {
            channelsMessageDto.setIdChannelsMessageDto(channelsMessage.getIdChannelsMessage());
            channelsMessageDto.setChannelId(channelsMessage.getChannel().getIdChannel());
            channelsMessageDto.setSenderId(channelsMessage.getSender().getIdUser());
            channelsMessageDto.setContent(channelsMessage.getContent());
            channelsMessageDto.setSentDate(channelsMessage.getSentDate());
        } catch (NullPointerException e) {
            log.error("ChannelsMessage Service - Some field was null", e.getMessage());
            throw new ChannelServiceExceptions("Message Service - Some field was null");

        } catch (Exception e) {
            log.error("ChannelsMessage Service Exception", e.getMessage());
            throw new ChannelServiceExceptions("Message Service Exception");
        }
        return channelsMessageDto;
    }
}
