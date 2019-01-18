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

public class ChannelRepositoryImpl implements ChannelRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public Channel saveMessage(Channel channel) {

        Long idChannel = channel.getIdChannel();
        String name = channel.getName();
        boolean isPublic = channel.getIsPublic();
        Photo profilePhoto = getPhoto(channel);
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
    public List<Channel> getListNameAndId() {
        String query ="SELECT new " + Channel.class.getName() + "(channel.idChannel, channel.name) FROM Channel channel";


        return entityManager.createQuery(query)
                .getResultList();
    }

    private User getUser(User channelOwner) {
        return entityManager.find(User.class, channelOwner.getIdUser());
    }

    private Photo getPhoto(Channel channel) {
        Photo profilePhoto = null;
        if (channel.getProfilePhoto() != null) {
            profilePhoto = entityManager.find(Photo.class, profilePhoto.getPhoto());
        }
        return profilePhoto;
    }

    private Set<User> getUsers(Channel channel) {
        Set<User> usersInChannel = null;
        if (!channel.getUsersInChannel().isEmpty()) {
            usersInChannel = usersInChannel.stream()
                    .map(user -> entityManager.find(User.class, user.getIdUser()))
                    .collect(Collectors.toSet());
        }else {
            usersInChannel = new HashSet<>();
        }
        return usersInChannel;
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
