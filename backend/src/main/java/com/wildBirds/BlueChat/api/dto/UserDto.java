package com.wildBirds.BlueChat.api.dto;

import java.util.List;

public class UserDto {

    private Long idUser;
    private String nick;
    private String password;
    private List<ChannelDto> channelsStaffed;
}
