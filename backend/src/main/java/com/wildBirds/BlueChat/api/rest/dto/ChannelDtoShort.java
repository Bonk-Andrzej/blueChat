package com.wildBirds.BlueChat.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDtoShort {
    private Long idChannel;
    private String name;
    private PhotoDto photoDto;
}
