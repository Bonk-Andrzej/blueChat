package com.wildBirds.BlueChat.domain.model;


import com.wildBirds.BlueChat.api.rest.dto.FriendsDto;
import com.wildBirds.BlueChat.api.rest.dto.UserDtoShort;

class UserContainFriendService {


    private PhotoService photoService;

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

        UserDtoShort friend = new UserDtoShort();

        checkWhoIsFriend(idUser, userContainFriend, friend);

        friendsDto.setFriend(friend);

        return friendsDto;
    }

    private void checkWhoIsFriend(Long idUser, UserContainFriend userContainFriend, UserDtoShort friend) {
        if(!userContainFriend.getUser1().getIdUser().equals(idUser)){

            friend.setIdUser(userContainFriend.getUser1().getIdUser());
            friend.setNick(userContainFriend.getUser1().getNick());
            friend.setPhotoDto(photoService.toDto(userContainFriend.getUser1().getProfilePhoto()));
        }else {
            friend.setIdUser(userContainFriend.getUser2().getIdUser());
            friend.setNick(userContainFriend.getUser2().getNick());
            friend.setPhotoDto(photoService.toDto(userContainFriend.getUser2().getProfilePhoto()));
        }
    }

    private void setId(UserContainFriend userContainFriend, FriendsDto friendsDto) {
        if (userContainFriend.getIdUserContainFriend() != null) {
            friendsDto.setIdFriendship(userContainFriend.getIdUserContainFriend());
        }
    }


}
