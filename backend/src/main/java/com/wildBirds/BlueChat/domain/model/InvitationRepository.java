package com.wildBirds.BlueChat.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface InvitationRepository extends JpaRepository<Invitation, Long> {

    List<Invitation> getInvitationByReceiverInvitationIdUser(Long idReceiver);

}
