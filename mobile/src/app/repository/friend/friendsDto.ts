import {UserDtoShort} from '../user/userDtoShort';

export class FriendsDto {
    public idFriendship: number;
    public friend: UserDtoShort;
    public dateFriendship : string;
    public noReadMessage: number;

}