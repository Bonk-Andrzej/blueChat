import {UserDtoShort} from '../user/userDtoShort';
import {PhotoDto} from '../photo/photoDto';

export class ChannelDto {
    private idChannel: number;
    private name: string;
    private isPublic: boolean;
    private userIdChannelOwner: UserDtoShort;
    private userList: Array<UserDtoShort>;
    private photoDto: PhotoDto;
}