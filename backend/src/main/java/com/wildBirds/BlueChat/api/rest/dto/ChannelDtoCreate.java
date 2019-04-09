package com.wildBirds.BlueChat.api.rest.dto;

import java.util.List;

public class ChannelDtoCreate {

    private String name;
    private boolean publicChannel;
    private Long userIdChannelOwner;
    private List<Long> userList;
    private PhotoDto photoDto;

    public ChannelDtoCreate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublicChannel() {
        return publicChannel;
    }

    public void setPublicChannel(boolean publicChannel) {
        this.publicChannel = publicChannel;
    }

    public Long getUserIdChannelOwner() {
        return userIdChannelOwner;
    }

    public void setUserIdChannelOwner(Long userIdChannelOwner) {
        this.userIdChannelOwner = userIdChannelOwner;
    }

    public List<Long> getUserList() {
        return userList;
    }

    public void setUserList(List<Long> userList) {
        this.userList = userList;
    }

    public PhotoDto getPhotoDto() {
        return photoDto;
    }

    public void setPhotoDto(PhotoDto photoDto) {
        this.photoDto = photoDto;
    }
}
