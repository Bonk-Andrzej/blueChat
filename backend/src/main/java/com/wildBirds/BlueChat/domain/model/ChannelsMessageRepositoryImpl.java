package com.wildBirds.BlueChat.domain.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
