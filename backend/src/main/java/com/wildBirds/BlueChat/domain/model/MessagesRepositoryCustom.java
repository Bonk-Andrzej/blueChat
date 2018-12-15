package com.wildBirds.BlueChat.domain.model;

import java.util.List;

interface MessagesRepositoryCustom {
    public List<Message> getConversation(Integer sender, Integer receiver, Integer limit, Integer toBound);
}
