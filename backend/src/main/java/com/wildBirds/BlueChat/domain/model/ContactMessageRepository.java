package com.wildBirds.BlueChat.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

    public List<ContactMessage> findAllByOrderBySentDateDesc();
}
