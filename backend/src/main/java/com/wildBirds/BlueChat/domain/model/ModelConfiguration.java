package com.wildBirds.BlueChat.domain.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ModelConfiguration {
    @Bean
    public ChannelFacade channelFacade() {
        return new ChannelFacade();
    }

    @Bean
    public ChannelsMessageFacade channelsMessageFacade() {
        return new ChannelsMessageFacade();
    }

    @Bean
    public MessageFacade messageFacade(){
        return new MessageFacade();
    }

    @Bean
    public UserFacade userFacade(){
        return new UserFacade();
    }
}
