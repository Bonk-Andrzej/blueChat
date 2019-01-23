package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;

import java.util.ArrayList;
import java.util.List;

class UserContainFriendService {


    private PhotoService photoService;
    private UserService userService;

    public UserContainFriendService(PhotoService photoService) {
        this.photoService = photoService;
    }

    public UserContainFriend toEntity(Long idLoggedUser, FriendsDto friendsDto) {
        UserContainFriend userContainFriend = new UserContainFriend();

        if (friendsDto.getIdFriendship() != null) {
            userContainFriend.setIdUserContainFriend(friendsDto.getIdFriendship());
        }

        User loggedUserEntity = new User();
        loggedUserEntity.setIdUser(idLoggedUser);

        User hisFriendEntity = new User();
        hisFriendEntity.setIdUser(friendsDto.getFriend().getIdUser());

        userContainFriend.setUser1(loggedUserEntity);
        userContainFriend.setUser2(hisFriendEntity);
        userContainFriend.setDateFriendShip(friendsDto.getDateFriendShip());

        return userContainFriend;
    }


    public FriendsDto toDto(Long idUser, UserContainFriend userContainFriend) {
        FriendsDto friendsDto = new FriendsDto();

        setId(userContainFriend, friendsDto);

        friendsDto.setDateFriendShip(userContainFriend.getDateFriendShip());

        friendsDto.setFriend(checkWhoIsFriend(idUser, userContainFriend));

        return friendsDto;
    }

    public List<FriendsDto> toDtoWithStatusMessage(Long idUser, List<UserContainFriend> friendship, List<Message> messageList) {

        List<FriendsDto> result = new ArrayList<>();

        for (UserContainFriend userContainFriend : friendship) {
            FriendsDto friendsDto = new FriendsDto();
            Long noReadMsg = 0L;
            friendsDto.setIdFriendship(userContainFriend.getIdUserContainFriend());
            friendsDto.setDateFriendShip(userContainFriend.getDateFriendShip());

            friendsDto.setFriend(checkWhoIsFriend(idUser, userContainFriend));

            for (Message message : messageList) {
                Long sender = message.getSender().getIdUser();
                if ((sender.equals(userContainFriend.getUser1().getIdUser())) || (sender.equals(userContainFriend.getUser2().getIdUser()))) {
                    noReadMsg = noReadMsg + 1;
                }
            }
            friendsDto.setNoReadMessage(noReadMsg);
            result.add(friendsDto);
        }
        return result;
    }

    private UserDtoShort checkWhoIsFriend(Long idUser, UserContainFriend userContainFriend) {

        UserDtoShort friend = new UserDtoShort();
        if(!userContainFriend.getUser1().getIdUser().equals(idUser)){

            friend.setIdUser(userContainFriend.getUser1().getIdUser());
            friend.setNick(userContainFriend.getUser1().getNick());
            friend.setPhotoDto(photoService.toDto(userContainFriend.getUser1().getProfilePhoto()));
        }else {
            friend.setIdUser(userContainFriend.getUser2().getIdUser());
            friend.setNick(userContainFriend.getUser2().getNick());
            friend.setPhotoDto(photoService.toDto(userContainFriend.getUser2().getProfilePhoto()));
        }
        return friend;
    }

    private void setId(UserContainFriend userContainFriend, FriendsDto friendsDto) {
        if (userContainFriend.getIdUserContainFriend() != null) {
            friendsDto.setIdFriendship(userContainFriend.getIdUserContainFriend());
        }
    }


}
