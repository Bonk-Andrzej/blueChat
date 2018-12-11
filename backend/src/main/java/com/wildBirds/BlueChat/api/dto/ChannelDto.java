package com.wildBirds.BlueChat.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDto {
    private Long idChanel;
    private String name;
    private UserDto channelOwner;
    private boolean isPublic;
    private List<UserDto> usersInChannel;
}
