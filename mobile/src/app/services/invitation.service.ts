import {Injectable} from '@angular/core';
import {InvitationRepositoryService} from '../repository/invitation/invitation-repository.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {InvitationDto} from '../repository/invitation/invitationDto';
import {UserProfileService} from './user-profile.service';
import {UserDtoShort} from '../repository/user/userDtoShort';
import {FriendRepositoryService} from '../repository/friend/friend-repository.service';
import {FriendsDto} from '../repository/friend/friendsDto';


@Injectable({
    providedIn: 'root'
})
export class InvitationService {

    invitationsList: BehaviorSubject<Array<InvitationDto>> = new BehaviorSubject([]);

    constructor(private invitationRepository: InvitationRepositoryService,
                private userProfileService: UserProfileService,
                private friendsRepository: FriendRepositoryService) {
        this.fetchInvitations();
    }

    private fetchInvitations() {
        this.userProfileService.userBeh.subscribe(async (user) => {
            if (user != null) {
                const result = await this.invitationRepository.getInvitations(user.getIdUser());
                this.invitationsList.next(result);
            }
        });
    }

    public getInvitations(): Observable<Array<InvitationDto>> {
        return this.invitationsList.asObservable();
    }

    public async acceptInvitation(invitationDto: InvitationDto) {
        console.log('ACCEPT INVITATION');
        let frendsDto = await this.invitationRepository.accept(invitationDto);
        this.userProfileService.addFrendDtoToFriend(frendsDto);

        let newInvitaionList = this.invitationsList.getValue().filter(value => !(value.idInvitation == invitationDto.idInvitation));
        this.invitationsList.next(newInvitaionList);
    }

    public sendInvitation(senderInvitation: number, receiverInvitation: number) {
        let invitationDto = new InvitationDto();

        console.log('senderInvitation>>>>>>>', senderInvitation);
        let sender = new UserDtoShort();
        sender.idUser = senderInvitation;
        invitationDto.senderInvitation = sender;


        let receiver = new UserDtoShort();
        receiver.idUser = receiverInvitation;
        invitationDto.receiverInvitation = receiver;


        invitationDto.receiverInvitation = receiver;
        console.log('receiverInvitation>>>>>>>', receiverInvitation);
        this.invitationRepository.invite(invitationDto);
    }

    public removeFriendship(idFriendship: number) {
        let friendsDto = new FriendsDto();
        friendsDto.idFriendship = idFriendship;
        return this.friendsRepository.removeFriendship(friendsDto);
    }
}
