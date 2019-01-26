package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.webSocket.controllers.MessageControllerWSR;
import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ModelConfiguration {

    @Bean
    ChannelFacade channelFacade(ChannelRepository channelRepository, ChannelService channelService, UserRepository userRepository) {
        return new ChannelFacade(channelRepository, channelService, userRepository);
    }

    @Bean
    ChannelsMessageFacade channelsMessageFacade(ChannelsMessageRepository channelsMessageRepository, ChannelsMessageService channelsMessageService) {
        return new ChannelsMessageFacade(channelsMessageRepository,channelsMessageService );
    }

    @Bean
    ContactMessageFacade contactMessageFacade(ContactMessageRepository contactMessageRepository, ContactMessageService contactMessageService){
        return new ContactMessageFacade(contactMessageRepository, contactMessageService);
    }

    @Bean
    MessageFacade messageFacade(MessageRepository messageRepository, MessageService messageService) {
        return new MessageFacade(messageRepository,messageService);
    }
    @Bean
    PhotoFacade photoFacade(PhotoRepository photoRepository, PhotoService photoService){
        return new PhotoFacade(photoRepository,photoService);
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository,UserService userService,ChannelFacade channelFacade, PhotoFacade photoFacade, MessageControllerWSR messageControllerWSR) {
        return new UserFacade(userRepository,userService, channelFacade, photoFacade, messageControllerWSR);
    }

    @Bean
    UserContainFriendFacade userContainFriendFacade(MessageControllerWSR wsr,UserContainFriendService userContainFriendService, UserContainFriendRepository userContainFriendRepository, MessageRepository messageRepository){
        return new UserContainFriendFacade(wsr,userContainFriendService, userContainFriendRepository,messageRepository );
    }
    @Bean
    UserWithLasMessageFacade userWithLasMessageFacade(MessageService messageService,MessageFacade messageFacade, PhotoService photoService){
        return new UserWithLasMessageFacade(messageService, messageFacade, photoService);
    }



    //services

    @Bean
    public ContactMessageService contactMessageService(){
        return new ContactMessageService();
    }

    @Bean
    public ChannelService channelService(UserService userService, PhotoService photoService) {
        return new ChannelService(userService, photoService);
    }

    @Bean
    public ChannelsMessageService channelsMessageService(ChannelService channelService, UserService userService) {
        return new ChannelsMessageService(channelService, userService);
    }

    @Bean
    public MessageService messageService(UserService userService) {
        return new MessageService(userService);
    }

    @Bean
    public UserService userService(PhotoService photoService) {
        return new UserService(photoService);
    }

    @Bean
    public PhotoService photoService(){
        return new PhotoService();
    }

    @Bean
    public UserContainFriendService userContainFriendService(PhotoService photoService){
        return new UserContainFriendService(photoService);
    }
}
