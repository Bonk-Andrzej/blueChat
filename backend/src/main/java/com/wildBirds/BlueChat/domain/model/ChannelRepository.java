package com.wildBirds.BlueChat.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

interface ChannelRepository extends JpaRepository<Channel, Long> , ChannelRepositoryCustom{

}
