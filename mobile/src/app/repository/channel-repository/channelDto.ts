import {UserDtoShort} from '../user-repository/userDtoShort';
import {PhotoDto} from '../photo-repository/photoDto';

export class ChannelDto {
    private id: number;
    private name: string;
    private isPublic: boolean;
    private channelOwner: UserDtoShort;
    private userList: Array<UserDtoShort>;
    private photo: PhotoDto;
}