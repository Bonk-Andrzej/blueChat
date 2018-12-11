package com.wildBirds.BlueChat.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

interface ChannelsMessagesRepository extends JpaRepository<ChannelsMessage, Long> {
}
