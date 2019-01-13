import {UserDtoShort} from '../user-repository/userDtoShort';
import {ChannelDtoShort} from '../channel-repository/channelDtoShort';

export class ChannelMessageDto {
    private id: number;
    private content: string;
    private sentDate: string;
    private isRead: boolean;
    private sender: UserDtoShort;
    private channel: ChannelDtoShort;

}