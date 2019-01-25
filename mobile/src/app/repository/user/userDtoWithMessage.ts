import {PhotoDto} from '../photo/photoDto';
import {MessageDto} from '../message/messageDto';

export class UserDtoWithMessage {


    public idUser :number;
    public nick : string;
    public photoDto : PhotoDto;
    public lastMessage : MessageDto;


    constructor() {
    }
}