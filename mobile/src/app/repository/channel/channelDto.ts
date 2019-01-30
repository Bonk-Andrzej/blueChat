import {UserDtoShort} from '../user/userDtoShort';
import {PhotoDto} from '../photo/photoDto';

export class ChannelDto {
    public idChannel: number;
    public name: string;
    public isPublic: boolean;
    public userIdChannelOwner: UserDtoShort;
    public userList: Array<UserDtoShort>;
    public photoDto: PhotoDto;
}