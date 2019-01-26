import {Injectable, OnInit} from '@angular/core';
import {InvitationRepositoryService} from '../repository/invitation/invitation-repository.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {InvitationDto} from '../repository/invitation/invitationDto';
import {UserProfileService} from './user-profile.service';


@Injectable({
    providedIn: 'root'
})
export class InvitationService  {


    invitations: BehaviorSubject<Array<InvitationDto>> = new BehaviorSubject([]);

    constructor(private invitationRepository: InvitationRepositoryService,
                private userProfileService: UserProfileService) {
console.log("START FRIENDS SERVICE")
        this.fetchInvitations();
    }


    private async fetchInvitations() {

        const result = await this.invitationRepository.getInvitations(this.userProfileService.getUser().getIdUser());
console.log('TUTAJ >>>>>>>>>>>', result);
        this.invitations.next(result);
    }

    public getInvitations(): Observable<Array<InvitationDto>> {
        return this.invitations.asObservable();
    }
}
