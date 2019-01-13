import {UserDtoShort} from '../user/userDtoShort';
import {PhotoDto} from '../photo/photoDto';

export class ChannelDto {
    private id: number;
    private name: string;
    private isPublic: boolean;
    private channelOwner: UserDtoShort;
    private userList: Array<UserDtoShort>;
    private photo: PhotoDto;
}