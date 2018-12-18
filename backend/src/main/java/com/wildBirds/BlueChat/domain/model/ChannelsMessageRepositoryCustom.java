package com.wildBirds.BlueChat.domain.model;

import java.util.List;

interface ChannelsMessageRepositoryCustom {

    List<ChannelsMessage> getConversation(Long idChannel, Integer limit, Integer toBound);

    ChannelsMessage saveMessage(ChannelsMessage channelsMessage);

}
