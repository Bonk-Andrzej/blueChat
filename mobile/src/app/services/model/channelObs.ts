import {ChannelDto} from "../../repository/channel/channelDto";
import {UserDtoShort} from "../../repository/user/userDtoShort";
import {PhotoDto} from "../../repository/photo/photoDto";


export class ChannelObs {

    private idChannel: number;
    private name: string;
    private isPublic: boolean;
    private userIdChannelOwner: UserDtoShort;
    private userList: Array<UserDtoShort>;
    private photoDto: PhotoDto;

    constructor(){
        this.photoDto = new PhotoDto();
        this.userList = [];
    }

    public static create(channelDto: ChannelDto): ChannelObs{
        const newChannel = new ChannelObs();
        newChannel.idChannel = channelDto.idChannel;
        newChannel.name = channelDto.name;
        newChannel.isPublic = channelDto.isPublic;
        newChannel.userIdChannelOwner = channelDto.userIdChannelOwner;
        newChannel.userList = channelDto.userList;
        newChannel.photoDto = channelDto.photoDto;
        return newChannel;
    }

    public getName(): string{
        return this.name;
    }

    public getPhoto(): PhotoDto{
        return this.photoDto;
    }
}