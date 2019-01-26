package com.wildBirds.BlueChat.domain.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class InvitationRepositoryImpl implements InvitationRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Invitation saveInvitation(Invitation invitation) {
        Invitation toSave = new Invitation();
        User receiver = invitation.getReceiverInvitation();
        User sender = invitation.getSenderInvitation();

        receiver = entityManager.find(User.class, receiver.getIdUser());
        sender = entityManager.find(User.class, sender.getIdUser());

        if(invitation.getIdInvitation() != null){
            toSave.setIdInvitation(invitation.getIdInvitation());
        }
        toSave.setDateInvitation(invitation.getDateInvitation());
        toSave.setReceiverInvitation(receiver);
        toSave.setSenderInvitation(sender);
        entityManager.persist(toSave);
//        toSave =entityManager.merge(toSave);
        return toSave;
    }
}
