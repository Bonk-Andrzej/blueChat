import {EventEmitter, Injectable, OnInit} from '@angular/core';
import {InvitationRepositoryService} from '../repository/invitation/invitation-repository.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {InvitationDto} from '../repository/invitation/invitationDto';
import {UserProfileService} from './user-profile.service';
import {UserDtoShort} from '../repository/user/userDtoShort';
import {FriendsDto} from '../repository/friend/friendsDto';


@Injectable({
    providedIn: 'root'
})
export class InvitationService  {


    invitationsList: BehaviorSubject<Array<InvitationDto>> = new BehaviorSubject([]);
    constructor(private invitationRepository: InvitationRepositoryService,
                private userProfileService: UserProfileService) {
        this.fetchInvitations();
    }

    private async fetchInvitations() {

        const result = await this.invitationRepository.getInvitations(this.userProfileService.getUser().getIdUser());
        this.invitationsList.next(result);
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
}
