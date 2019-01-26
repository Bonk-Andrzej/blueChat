package com.wildBirds.BlueChat.domain.model;

import org.junit.Assert;
import org.junit.Test;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

public class InvitationRepositoryTest extends ConfigurationTest {



    @Test
    @Transactional
    public void saveNewInvitation() {
        //given
        User sender = new User();
        sender.setNick("igorInvitation");
        sender.setPassword("password");
        sender = userRepository.save(sender);

        User receiver = new User();
        receiver.setNick("pawelInvitation");
        receiver.setPassword("password");
        receiver = userRepository.save(receiver);


        Invitation invitation = new Invitation();
        invitation.setSenderInvitation(sender);
        invitation.setReceiverInvitation(receiver);
        invitation.setDateInvitation(Instant.now());


        //when
        Invitation save = invitationRepository.save(invitation);

        //then

        Assert.assertNotNull(save);
        Assert.assertNotNull(save.getIdInvitation());


    }

    @Test
    @Transactional
    public void shouldRemoveInvitation(){
        //given
        User sender = new User();
        sender.setNick("igorInvitation2");
        sender.setPassword("password");
        sender = userRepository.save(sender);

        User receiver = new User();
        receiver.setNick("pawelInvitation2");
        receiver.setPassword("password");
        receiver = userRepository.save(receiver);


        Invitation invitation = new Invitation();
        invitation.setSenderInvitation(sender);
        invitation.setReceiverInvitation(receiver);
        invitation.setDateInvitation(Instant.now());
        invitationRepository.save(invitation);

        //when
        invitationRepository.delete(invitation);
        //then

        try {
            invitationRepository.getOne(invitation.getIdInvitation());
            Assert.assertTrue(false);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }


    }
    @Test
    @Transactional
    public void getInvitationBySenderInvitation(){

        //given
        User sender = new User();
        sender.setNick("igorInvitation3");
        sender.setPassword("password");
        sender = userRepository.save(sender);

        User receiver = new User();
        receiver.setNick("pawelInvitation3");
        receiver.setPassword("password");
        receiver = userRepository.save(receiver);

        User sender2 = new User();
        sender2.setNick("pawelInvitation4");
        sender2.setPassword("password");
        sender2 = userRepository.save(sender2);

        Invitation invitation = new Invitation();
        invitation.setSenderInvitation(sender2);
        invitation.setReceiverInvitation(receiver);
        invitation.setDateInvitation(Instant.now());
        invitationRepository.save(invitation);


        Invitation invitation2 = new Invitation();
        invitation2.setSenderInvitation(sender);
        invitation2.setReceiverInvitation(receiver);
        invitation2.setDateInvitation(Instant.now());
        invitationRepository.save(invitation2);


        //whem
        List<Invitation> result = invitationRepository.getInvitationByReceiverInvitationIdUser(receiver.getIdUser());

        //
        Assert.assertEquals(2, result.size());


    }
}