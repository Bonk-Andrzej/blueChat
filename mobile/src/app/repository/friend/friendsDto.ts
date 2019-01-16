import {UserDtoShort} from '../user/userDtoShort';

export class FriendsDto {
    private id: number;
    private you: UserDtoShort;
    private friend: UserDtoShort;
    private dateFriendship : string;

}