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
public class UserDto {

    private Long idUser;
    private String nick;
//    private List<ChannelDto> channelsStaffed;
}
