package com.wildBirds.BlueChat.domain.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


class MessageRepositoryImpl implements MessagesRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;


    // TODO: 14.12.2018 HAVE TO TESTING
    public List<Message> getConversation(Long idSender, Long idReceiver, Integer limit, Integer toBound) {

        String query = "SELECT messages FROM Message messages " +
                "JOIN messages.receiver receiver " +
                "JOIN messages.sender sender " +
                "WHERE receiver.idUser =: idReceiver " +
                "AND sender.idUser =: idSender OR " +
                "receiver.idUser =: idSender AND " +
                "sender.idUser =: idReceiver " +
                "ORDER BY messages.sentDate ASC";

        return entityManager.createQuery(query)
                .setParameter("idSender", idSender)
                .setParameter("idReceiver", idReceiver)
                .setMaxResults(limit)
                .setFirstResult(toBound)
                .getResultList();
    }

    @Override
    @Transactional
    public Message saveMessage(Message message) {
        entityManager.persist(message);
        return entityManager.find(Message.class, message.getIdMessage());


    }
}
