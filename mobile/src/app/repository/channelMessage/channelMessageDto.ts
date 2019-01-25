import {UserDtoShort} from '../user/userDtoShort';
import {ChannelDtoShort} from '../channel/channelDtoShort';

export class ChannelMessageDto {
    public idChannelsMessageDto: number;
    public content: string;
    public sentDate: string;
    public readBy: Array<number>;
    public senderId: number
    public channelId: number;

}