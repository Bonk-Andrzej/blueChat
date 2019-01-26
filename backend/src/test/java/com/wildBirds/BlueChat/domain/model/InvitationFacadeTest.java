package com.wildBirds.BlueChat.domain.model;

import com.wildBirds.BlueChat.api.rest.dto.InvitationDto;
import org.junit.Assert;
import org.junit.Test;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

public class InvitationFacadeTest extends ConfigurationTest{

    @Test
    public void saveInvitation() {
    }

    @Test
    @Transactional
    public void getUserInvitation() {
        //given
        User sender = new User();
        sender.setNick("igorInvitationFacade33");
        sender.setPassword("password");
        sender = userRepository.save(sender);

        User receiver = new User();
        receiver.setNick("pawelInvitationFacade33");
        receiver.setPassword("password");
        receiver = userRepository.save(receiver);

        User sender2 = new User();
        sender2.setNick("pawelInvitationFacade43");
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
        List<InvitationDto> result = invitationFacade.getUserInvitation(receiver.getIdUser());

        //
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void removeInvitarion() {
    }
}