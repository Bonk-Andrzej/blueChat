package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.webSocket.controllers.MessageControllerWSR;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
class ModelConfiguration {

    @Bean
    ChannelFacade channelFacade(UserService userService, ChannelRepository channelRepository, ChannelService channelService, UserRepository userRepository) {
        return new ChannelFacade(channelRepository, channelService, userRepository, userService);
    }

    @Bean
    ChannelsMessageFacade channelsMessageFacade(ChannelsMessageRepository channelsMessageRepository, ChannelsMessageService channelsMessageService) {
        return new ChannelsMessageFacade(channelsMessageRepository, channelsMessageService);
    }

    @Bean
    ContactMessageFacade contactMessageFacade(ContactMessageRepository contactMessageRepository, ContactMessageService contactMessageService) {
        return new ContactMessageFacade(contactMessageRepository, contactMessageService);
    }

    @Bean
    InvitationFacade invitationFacade(InvitationRepository invitationRepository, InvitationService invitationService, UserContainFriendFacade friendFacade) {
        return new InvitationFacade(invitationRepository, invitationService, friendFacade);
    }

    @Bean
    MessageFacade messageFacade(MessageRepository messageRepository, MessageService messageService) {
        return new MessageFacade(messageRepository, messageService);
    }

    @Bean
    PhotoFacade photoFacade(PhotoRepository photoRepository, PhotoService photoService) {
        return new PhotoFacade(photoRepository, photoService);
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository, UserService userService, ChannelFacade channelFacade, PhotoFacade photoFacade, MessageControllerWSR messageControllerWSR) {
        return new UserFacade(userRepository, userService, channelFacade, photoFacade, messageControllerWSR);
    }

    @Bean
    UserContainFriendFacade userContainFriendFacade(MessageControllerWSR wsr, UserContainFriendService userContainFriendService, UserContainFriendRepository userContainFriendRepository, MessageRepository messageRepository) {
        return new UserContainFriendFacade(wsr, userContainFriendService, userContainFriendRepository, messageRepository);
    }

    @Bean
    UserWithLasMessageFacade userWithLasMessageFacade(MessageService messageService, MessageFacade messageFacade, PhotoService photoService) {
        return new UserWithLasMessageFacade(messageService, messageFacade, photoService);
    }
    @Bean
    EmailFacade emailFacade(){
        return new EmailFacade();
    }



    //services

//    @Bean
//    public EmailService emailService(){
//        return new EmailService();
//    }
//    @Bean
//    public JavaMailSender javaMailSender(){
//        return new JavaMailSenderImpl();
//    }

    @Bean
    public ContactMessageService contactMessageService() {
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
    public InvitationService invitationService(UserService userService) {
        return new InvitationService(userService);
    }


    @Bean
    public UserService userService(PhotoService photoService) {
        return new UserService(photoService);
    }

    @Bean
    public PhotoService photoService() {
        return new PhotoService();
    }

    @Bean
    public UserContainFriendService userContainFriendService(PhotoService photoService) {
        return new UserContainFriendService(photoService);
    }
}
