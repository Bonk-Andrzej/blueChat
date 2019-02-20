import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserProfileService} from '../../services/user-profile.service';
import {UserDtoWithMessage} from '../../repository/user/userDtoWithMessage';
import {Observable} from 'rxjs';
import {ConversationService} from '../../services/conversation.service';
import {UserShortObs} from '../../services/model/userShortObs';
import {Router} from '@angular/router';
import {InvitationService} from '../../services/invitation.service';
import {InvitationDto} from '../../repository/invitation/invitationDto';

@Component({
    selector: 'app-main-login',
    templateUrl: './main-login.component.html',
    styleUrls: ['./main-login.component.scss']
})
export class MainLoginComponent implements OnInit, OnDestroy {

    usersWithNewMessage: Observable<Array<UserDtoWithMessage>>;
    invitations: Observable<Array<InvitationDto>>;

    constructor(private router: Router,
                private useService: UserProfileService,
                private conversationService: ConversationService,
                private invitationService: InvitationService) {
    }

    ngOnInit() {
        this.usersWithNewMessage = this.useService.getUsersWuthMsg();
        this.invitations = this.invitationService.getInvitations();
        try{
            navigator.splashscreen.hide();
        }catch (e) {}
    }

    ngOnDestroy(): void {
        this.invitationService.cleanInviation();
    }

    acceptInvitation(invitationDto: InvitationDto) {
        this.invitationService.acceptInvitation(invitationDto)
    }

    startConversation(usersWithNewMessage: UserDtoWithMessage) {
        let user: UserShortObs = new UserShortObs();

        console.log(' >>>>>>>>>> Z LISTY' + usersWithNewMessage)
        user.setIdUser(usersWithNewMessage.idUser);
        user.setNick(usersWithNewMessage.nick);

        console.log('>>>>>>>>>>>>>>>>>>>>' + user.getNick());
        // user.setPhoto(usersWithNewMessage.photoDto);
        this.conversationService.startConversationWithUser(user).catch()
        this.useService.removeUserWithMsg(usersWithNewMessage);
    }
}
