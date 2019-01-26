package com.wildBirds.BlueChat.api.rest.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChannelsMessageDto {

    private Long idChannelsMessageDto;
    private String content;
    private Instant sentDate;
    private UserDtoShort sender;
    private ChannelDtoShort channel;
}
