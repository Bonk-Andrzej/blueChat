package com.wildBirds.BlueChat.domain.model;

import java.util.List;

interface ChannelRepositoryCustom {

    Channel saveMessage(Channel channel);
    List<Channel> getListNameAndId();

}
