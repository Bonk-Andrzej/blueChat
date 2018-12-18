package com.wildBirds.BlueChat.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

interface ChannelsMessageRepository extends JpaRepository<ChannelsMessage, Long>, ChannelsMessageRepositoryCustom {
}
