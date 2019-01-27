package com.wildBirds.BlueChat.domain.model;

import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class ChannelRepositoryImpl implements ChannelRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public Channel saveChannel(Channel channel) {

        Long idChannel = channel.getIdChannel();
        String name = channel.getName();
        boolean isPublic = channel.getIsPublic();
        Photo profilePhoto = null;
        if (channel.getProfilePhoto() != null) {
            profilePhoto = channel.getProfilePhoto();
        }

        User channelOwner = channel.getChannelOwner();
        User userJpa = getUser(channelOwner);
        Set<User> usersInChannel = getUsers(channel);
        List<ChannelsMessage> channelsMessageList = getChannelsMessages(channel);


        Channel channelToSave = new Channel();
        channelToSave.setIdChannel(idChannel);
        channelToSave.setName(name);
        channelToSave.setChannelOwner(userJpa);
        channelToSave.setProfilePhoto(profilePhoto);
        channelToSave.setUsersInChannel(usersInChannel);
        channelToSave.setIsPublic(isPublic);
        channelToSave.setChannelsMessage(channelsMessageList);

        Channel response = null;
        try {
            response = entityManager.merge(channelToSave);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return response;
    }

    @Override
    public List<Channel> getListNameIdPhoto() {
        String query = "SELECT new " + Channel.class.getName() + "(channel.idChannel, channel.name, photo)  FROM Channel channel " +
                "JOIN channel.profilePhoto photo";


        return entityManager.createQuery(query)
                .getResultList();
    }

    @Override
    public List<Channel> getChannels(Long idUser) {

        String query = "SELECT channel FROM Channel channel " +
                "JOIN channel.usersInChannel users " +
                "WHERE users.idUser =: idUser";

        return entityManager.createQuery(query)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public List<Channel> getPublicChannels() {

        String query = "SELECT channel FROM Channel channel " +
                "WHERE channel.isPublic =: isPublic";

        return entityManager.createQuery(query)
                .setParameter("isPublic", true)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<User> getChannelMembers(Long channelId) {
        String query = "SELECT user FROM User user " +
                "JOIN user.channelsStaffed channel " +
                "WHERE channel.idChannel = :idChannel";

        return entityManager.createQuery(query)
                .setParameter("idChannel", channelId)
                .getResultList();
    }

    @Override
    public List<Channel> getByPhrase(String phrase) {

        String query = "SELECT channel FROM Channel channel " +
                "WHERE channel.name like :phrase AND " +
                "channel.isPublic =: isPublic";

        return entityManager.createQuery(query)
                .setParameter("phrase", "%" + phrase + "%")
                .setParameter("isPublic", true)
                .setMaxResults(10)
                .getResultList();
    }


    private User getUser(User channelOwner) {
        return entityManager.find(User.class, channelOwner.getIdUser());
    }

    private Set<User> getUsers(Channel channel) {
        if (!channel.getUsersInChannel().isEmpty()) {
            Set<User> usersInChannel = channel.getUsersInChannel();
            usersInChannel = usersInChannel.stream()
                    .map(user -> entityManager.find(User.class, user.getIdUser()))
                    .collect(Collectors.toSet());
            return usersInChannel;
        }else {
            Set<User> usersInChannel = new HashSet<>();
            return usersInChannel;
        }
    }

    private List<ChannelsMessage> getChannelsMessages(Channel channel) {
        List<ChannelsMessage> channelsMessageList = null;
        if (!channel.getChannelsMessage().isEmpty()) {
            channelsMessageList = channelsMessageList.stream()
                    .map(channelsMessage1 -> entityManager.find(ChannelsMessage.class, channelsMessage1.getIdChannelsMessage()))
                    .collect(Collectors.toList());
        } else {
            channelsMessageList = new ArrayList<>();
        }
        return channelsMessageList;
    }
}
