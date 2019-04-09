import {PhotoDto} from '../photo/photoDto';

export class ChannelDtoCreate {
    public name: string;
    public publicChannel: boolean;
    public userIdChannelOwner: number;
    public userList: Array<number>;
    public photoDto: PhotoDto;


    constructor() {
    }
}