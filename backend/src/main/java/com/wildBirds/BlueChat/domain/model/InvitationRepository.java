package com.wildBirds.BlueChat.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface InvitationRepository extends JpaRepository<Invitation, Long>, InvitationRepositoryCustom{

    List<Invitation> getInvitationByReceiverInvitationIdUser(Long idReceiver);
    Invitation saveInvitation(Invitation invitation);

}
