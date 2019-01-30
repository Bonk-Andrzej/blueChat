package com.wildBirds.BlueChat.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ConfigurationTest {

    private Logger logger = LoggerFactory.getLogger(ConfigurationTest.class);

    @Autowired
    protected ChannelFacade channelFacade;

    @Autowired
    protected ChannelRepository channelRepository;

    @Autowired
    protected ChannelService channelService;



    @Autowired
    protected ChannelsMessageRepository chanMsgRep;

    @Autowired
    protected ChannelsMessageFacade channelsMessageFacade;


    @Autowired
    protected ContactMessageRepository contactMessageRepository;

    @Autowired
    protected ContactMessageFacade contactMessageFacade;


    @Autowired
    protected InvitationRepository invitationRepository;

    @Autowired
    protected InvitationService invitationService;

    @Autowired
    protected InvitationFacade invitationFacade;


    @Autowired
    protected UserContainFriendRepository userContainFriendRepository;

    @Autowired
    protected UserContainFriendFacade userContainFriendFacade;


    @Autowired
    protected MessageFacade messageFacade;

    @Autowired
    protected MessageRepository messageRepository;

    @Autowired
    protected MessageService messageService;


    @Autowired
    protected PhotoRepository photoRepository;


    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserFacade userFacade;

    @Autowired
    protected UserService userService;

    @Autowired
    protected EmailFacade emailFacade;


    @Autowired
    protected UserWithLasMessageFacade userWithLasMessageFacade;


    @Test
    public void contextLoads() {
        logger.info("Tests start ...");
    }

}
