package com.wildBirds.BlueChat.domain.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class MessageRepositoryImpl implements MessageRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Message> getConversation(Long idSender, Long idReceiver, Integer limit, Integer toBound) {

        String query = "SELECT messages FROM Message messages " +
                "JOIN messages.receiver receiver " +
                "JOIN messages.sender sender " +
                "WHERE receiver.idUser =: idReceiver " +
                "AND sender.idUser =: idSender OR " +
                "receiver.idUser =: idSender AND " +
                "sender.idUser =: idReceiver " +
                "ORDER BY messages.sentDate DESC ";

        List<Message> resultList = entityManager.createQuery(query)
                .setParameter("idSender", idSender)
                .setParameter("idReceiver", idReceiver)
                .setMaxResults(limit)
                .setFirstResult(toBound)
                .getResultList();

        return resultList.stream()
                .sorted(Comparator.comparing(Message::getSentDate))
                .collect(Collectors.toList());

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

        entityManager.persist(msgToSave);

        return msgToSave;
    }

    @Override
    public List<Object> lastMessages(Long idUser) {
        String query = "SELECT COUNT(DISTINCT sender), COUNT(DISTINCT receiver) , message, message.sentDate FROM Message message " +
                "JOIN message.sender sender " +
                "JOIN message.receiver receiver " +
                "WHERE receiver.idUser =: idUser OR " +
                "sender.idUser =: idUser " +
                "GROUP BY message.sender , message.idMessage " +
                "ORDER BY message.sentDate ASC ";

//count(distinct(r.user))
        return entityManager.createQuery(query)
                .setMaxResults(10)
                .setParameter("idUser", idUser)
                .getResultList();

    }


    @Override
    public List<Message> getNoReadMessages(Long idUser) {

        String query = "SELECT message FROM  Message message " +
                "JOIN message.receiver receiver " +
                "WHERE receiver.idUser =: idUser " +
                "AND message.isRead =: isRead " +
                "ORDER BY message.sentDate DESC ";

        return entityManager.createQuery(query)
                .setParameter("idUser", idUser)
                .setParameter("isRead", false)
                .getResultList();

    }

}
