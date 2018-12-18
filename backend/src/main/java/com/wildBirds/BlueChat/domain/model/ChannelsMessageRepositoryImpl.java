package com.wildBirds.BlueChat.domain.model;

import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

class ChannelsMessageRepositoryImpl implements ChannelsMessageRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<ChannelsMessage> getConversation(Long idChannel, Integer limit, Integer toBound) {

        String query ="SELECT chanMesg FROM ChannelsMessage chanMesg " +
                "JOIN chanMesg.channel channel " +
                "WHERE channel.idChannel =: idChannel " +
                "ORDER BY chanMesg.sentDate ASC";



        return entityManager.createQuery(query)
                .setParameter("idChannel", idChannel)
                .setMaxResults(limit)
                .setFirstResult(toBound)
                .getResultList();

    }

    @Override
    @Transactional
    public ChannelsMessage saveMessage(ChannelsMessage channelsMessage) {
        String content = channelsMessage.getContent();
        Instant sentDate = channelsMessage.getSentDate();
        Long idMessageGroup = channelsMessage.getIdMessageGroup();

        User sender = channelsMessage.getSender();
        Channel channel = channelsMessage.getChannel();
        sender = entityManager.find(User.class, sender.getIdUser());
        channel = entityManager.find(Channel.class, channel.getIdChannel());

        ChannelsMessage chanMsgToSave = new ChannelsMessage();
        chanMsgToSave.setContent(content);
        chanMsgToSave.setSentDate(sentDate);
        chanMsgToSave.setIdMessageGroup(idMessageGroup);
        chanMsgToSave.setSender(sender);
        chanMsgToSave.setChannel(channel);

        ChannelsMessage response = null;
        try {
            response = entityManager.merge(chanMsgToSave);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return response;
    }
}
