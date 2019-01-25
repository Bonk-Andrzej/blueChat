package com.wildBirds.BlueChat.domain.model;

import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class ChannelsMessageRepositoryImpl implements ChannelsMessageRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<ChannelsMessage> getConversation(Long idChannel, Integer limit, Integer toBound) {

        String query ="SELECT chanMesg FROM ChannelsMessage chanMesg " +
                "JOIN chanMesg.channel channel " +
                "WHERE channel.idChannel =: idChannel " +
                "ORDER BY chanMesg.sentDate DESC ";


        List<ChannelsMessage> resultList = entityManager.createQuery(query)
                .setParameter("idChannel", idChannel)
                .setMaxResults(limit)
                .setFirstResult(toBound)
                .getResultList();

        return resultList.stream()
                .sorted(Comparator.comparing(ChannelsMessage::getSentDate))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ChannelsMessage saveMessage(ChannelsMessage channelsMessage) {
        String content = channelsMessage.getContent();
        Instant sentDate = channelsMessage.getSentDate();
        Long idMessageGroup = channelsMessage.getIdChannelsMessage();

        User sender = channelsMessage.getSender();
        Channel channel = channelsMessage.getChannel();
        sender = entityManager.find(User.class, sender.getIdUser());
        channel = entityManager.find(Channel.class, channel.getIdChannel());

        ChannelsMessage chanMsgToSave = new ChannelsMessage();
        chanMsgToSave.setContent(content);
        chanMsgToSave.setSentDate(sentDate);
        chanMsgToSave.setIdChannelsMessage(idMessageGroup);
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
