package com.wildBirds.BlueChat.domain.model;

import java.util.List;

interface ChannelRepositoryCustom {

    Channel saveChannel(Channel channel);
    List<Channel> getListNameIdPhoto();
    List<Channel> getChannels(Long idUser);

}
