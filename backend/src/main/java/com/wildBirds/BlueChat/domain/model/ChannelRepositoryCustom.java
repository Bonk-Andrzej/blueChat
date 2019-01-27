package com.wildBirds.BlueChat.domain.model;

import java.util.List;

interface ChannelRepositoryCustom {

    Channel saveChannel(Channel channel);
    List<Channel> getListNameIdPhoto();
    List<Channel> getChannels(Long idUser);
    List<Channel> getPublicChannels();
    List<User> getChannelMembers(Long ChannelId);
    List<Channel> getByPhrase(String phrase);

}
