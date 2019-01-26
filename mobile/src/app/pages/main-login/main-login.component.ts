import {Component, OnInit} from '@angular/core';
import {UserProfileService} from '../../services/user-profile.service';
import {UserDtoWithMessage} from '../../repository/user/userDtoWithMessage';
import {Observable} from 'rxjs';
import {ConversationService} from '../../services/conversation.service';
import {UserShortObs} from '../../services/model/userShortObs';
import {Router} from '@angular/router';
import {FriendProfileService} from '../../services/friend-profile.service';
import {InvitationDto} from '../../repository/invitation/invitationDto';

@Component({
    selector: 'app-main-login',
    templateUrl: './main-login.component.html',
    styleUrls: ['./main-login.component.scss']
})
export class MainLoginComponent implements OnInit {

    usersWithNewMessage: Observable<Array<UserDtoWithMessage>>;
    invitations: Observable<Array<InvitationDto>>;

    constructor(private router: Router,
                private useService: UserProfileService,
                private conversationService: ConversationService,
                private friendsService: FriendProfileService) {
    }

    ngOnInit() {
        this.usersWithNewMessage = this.useService.getUsersWuthMsg();
        this.invitations = this.friendsService.getInvitations();
    }

    getInvitation() {
        alert('Working at this... Here you will get a invitations to friends');
    }

    startConversation(usersWithNewMessage: UserDtoWithMessage) {
        let user: UserShortObs = new UserShortObs();

        console.log(' >>>>>>>>>> Z LISTY'  +usersWithNewMessage)
        user.setIdUser(usersWithNewMessage.idUser);
        user.setNick(usersWithNewMessage.nick);

        console.log('>>>>>>>>>>>>>>>>>>>>'  + user.getNick());
        // user.setPhoto(usersWithNewMessage.photoDto);
        this.conversationService.startConversationWithUser(user);
        this.router.navigateByUrl('/conversation')
    }
}
