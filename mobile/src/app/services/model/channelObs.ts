import {ChannelDto} from "../../repository/channel/channelDto";
import {UserDtoShort} from "../../repository/user/userDtoShort";
import {PhotoDto} from "../../repository/photo/photoDto";
import {UserShortObs} from "./userShortObs";
import {BehaviorSubject, Observable} from "rxjs";


export class ChannelObs {

    private idChannel: number;
    private name: string;
    private isPublic: boolean;
    private userIdChannelOwner: UserDtoShort;
    private userList: BehaviorSubject<Array<UserShortObs>>;
    private photoDto: PhotoDto;

    constructor(){
        this.photoDto = new PhotoDto();
        this.userList = new BehaviorSubject<Array<UserShortObs>>([])
    }

    public static create(channelDto: ChannelDto): ChannelObs{
        const newChannel = new ChannelObs();
        newChannel.idChannel = channelDto.idChannel;
        newChannel.name = channelDto.name;
        newChannel.publicChannel = channelDto.isPublic;
        newChannel.userIdChannelOwner = channelDto.userIdChannelOwner;
        newChannel.userList.next(channelDto.userList.map(user => UserShortObs.create(user)));
        newChannel.photoDto = channelDto.photoDto;
        return newChannel;
    }

    public getIdChannel(){
        return this.idChannel;
    }

    public getName(): string{
        return this.name;
    }

    public getPhoto(): PhotoDto{
        return this.photoDto;
    }

    public getMembersList():Array<UserShortObs>{
        return this.userList.getValue()
    }

    public getMembersListObs():Observable<Array<UserShortObs>>{
        return this.userList.asObservable()
    }
}