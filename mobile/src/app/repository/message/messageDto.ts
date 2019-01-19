import {UserDtoShort} from '../user/userDtoShort';

export class MessageDto {
    public idMessage: number;
    public content: string;
    public sentDate: string;
    public isRead: boolean;
    public senderId: number;
    public idReceiver: number;
}