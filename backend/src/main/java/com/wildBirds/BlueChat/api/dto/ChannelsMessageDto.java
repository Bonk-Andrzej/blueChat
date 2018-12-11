package com.wildBirds.BlueChat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChannelsMessageDto {

    private Long idMessageGroup;
    private UserDto sender;
    private ChannelDto channel;
    private String content;
    private Instant sentDate;
}
