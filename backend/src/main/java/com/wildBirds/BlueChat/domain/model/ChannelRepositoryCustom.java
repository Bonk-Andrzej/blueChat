package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;

import java.util.List;

interface ChannelRepositoryCustom {

    Channel saveChannel(Channel channel);
    List<Channel> getListNameIdPhoto();
    List<Channel> getChannels(Long idUser);
    List<Channel> getPublicChannels();
    List<User> getChannelMembers(Long ChannelId);

}
