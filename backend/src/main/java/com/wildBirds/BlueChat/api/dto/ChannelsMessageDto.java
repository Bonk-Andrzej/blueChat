package com.wildBirds.BlueChat.api.dto;

import java.time.Instant;

public class ChannelsMessageDto {

    private Long idMessageGroup;
    private UserDto sender;
    private ChannelDto channel;
    private String content;
    private Instant sentDate;
}
