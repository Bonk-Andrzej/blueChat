package com.wildBirds.BlueChat.domain.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

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

    @Bean
    public ChannelService channelService() {
        return new ChannelService(userService());
    }

    @Bean
    public MessageService messageService() {
        return new MessageService(userService());
    }

    @Bean
    public ChannelsMessageService channelsMessageService() {
        return new ChannelsMessageService(channelService(), userService());
    }
    @Bean
    public UserService userService() {
        return new UserService();
    }

}
