import {UserDtoShort} from '../user/userDtoShort';

export class InvitationDto {

    public idInvitation: number;
    public senderInvitation: UserDtoShort;
    public receiverInvitation: UserDtoShort;
    public dateInvitation: string;

}
