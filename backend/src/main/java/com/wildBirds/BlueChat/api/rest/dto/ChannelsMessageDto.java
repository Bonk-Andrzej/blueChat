package com.wildBirds.BlueChat.api.rest.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChannelsMessageDto {

    private Long idMessageGroup;
    private UserDto sender;
    private ChannelDto channel;
    private String content;
    private Instant sentDate;
}
