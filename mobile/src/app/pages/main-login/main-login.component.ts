import {Component, OnInit} from '@angular/core';
import {UserProfileService} from '../../services/user-profile.service';
import {UserDtoWithMessage} from '../../repository/user/userDtoWithMessage';
import {Observable} from 'rxjs';
import {ConversationService} from '../../services/conversation.service';
import {UserShortObs} from '../../services/model/userShortObs';
import {Router} from '@angular/router';

@Component({
    selector: 'app-main-login',
    templateUrl: './main-login.component.html',
    styleUrls: ['./main-login.component.scss']
})
export class MainLoginComponent implements OnInit {

    newMessageDTOs = [
        {
            'idMessage': 1,
            'content': 'Thanks',
            'userNick': 'Marian Nowak',
        },
        {
            'idMessage': 2,
            'content': 'What are you doing today at the morning ?.',
            'userNick': 'Ola Taraska',
        },
        {
            'idMessage': 3,
            'content': 'I\'m fine thank you so much :)',
            'userNick': 'Anna Ostrowska',
        },
        {
            'idMessage': 3,
            'content': 'I\'m fine thank you so much :)',
            'userNick': 'Anna Ostrowska',
        },
        {
            'idMessage': 3,
            'content': 'I\'m fine thank you so much :)',
            'userNick': 'Anna Ostrowska',
        },

    ];
    usersWithNewMessage: Observable<Array<UserDtoWithMessage>>;

    constructor(private router: Router,
                private useService: UserProfileService,
                private conversationService: ConversationService) {
    }

    ngOnInit() {
        this.usersWithNewMessage = this.useService.getUsersWuthMsg();
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
