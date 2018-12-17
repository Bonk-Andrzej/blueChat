package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.webSocket.controllers.MessageControllerWSR;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ModelConfiguration {

    @Bean
    ChannelFacade channelFacade(ChannelRepository channelRepository, ChannelService channelService, UserRepository userRepository) {
        return new ChannelFacade(channelRepository, channelService, userRepository);
    }

    @Bean
    ChannelsMessageFacade channelsMessageFacade() {
        return new ChannelsMessageFacade();
    }

    @Bean
    MessageFacade messageFacade(MessageRepository messageRepository) {
        return new MessageFacade(messageRepository,messageService());
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository, MessageControllerWSR messageControllerWSR) {
        return new UserFacade(userRepository, userService(), messageControllerWSR);
    }


    @Bean
    public ChannelService channelService(UserService userService) {
        return new ChannelService(userService);
    }

    @Bean
    public MessageService messageService() {
        return new MessageService();
    }

    @Bean
    public ChannelsMessageService channelsMessageService(ChannelService channelService, UserService userService) {
        return new ChannelsMessageService(channelService, userService);
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public MessageRepositoryImpl messageRepositoryImpl(){
        return new MessageRepositoryImpl();
    }
}
