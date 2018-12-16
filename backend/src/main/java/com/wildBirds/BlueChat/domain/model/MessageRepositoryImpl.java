package com.wildBirds.BlueChat.domain.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.Instant;
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
        Message msgToSave = new Message();

        String content = message.getContent();
        User sender = message.getSender();
        User receiver = message.getReceiver();
        Instant sentDate = message.getSentDate();

        sender = entityManager.find(User.class, sender.getIdUser());
        receiver = entityManager.find(User.class, receiver.getIdUser());

        msgToSave.setSender(sender);
        msgToSave.setReceiver(receiver);
        msgToSave.setContent(content);
        msgToSave.setSentDate(sentDate);

// TODO: 16.12.2018 merge ??? message have null ID

        Message savedMsg = entityManager.merge(msgToSave);


//        entityManager.persist(message);
//        return entityManager.find(Message.class, message.getIdMessage());
        return savedMsg;
    }
}
