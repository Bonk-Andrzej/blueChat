package com.wildBirds.BlueChat.domain.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ModelConfiguration {

    @Bean
    ChannelFacade channelFacade() {
        return new ChannelFacade();
    }

    @Bean
    ChannelsMessageFacade channelsMessageFacade() {
        return new ChannelsMessageFacade();
    }

    @Bean
    MessageFacade messageFacade() {
        return new MessageFacade();
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository) {
        return new UserFacade(userRepository, userService());
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
