package com.wildBirds.BlueChat.domain.model;

import java.util.List;

public interface UserContainFriendCustom {
    List<UserContainFriend> getUserFriendship(Long idUser);
}
