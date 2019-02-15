import {Component, OnInit} from '@angular/core';
import {UserProfileService} from './services/user-profile.service';
import {WSRClientService} from './WSRClient/wsrclient.service';
import {ChangeService} from './services/change.service';
import {ColorService} from './services/background/color.service';
import {OwnEmojiServiceService} from './services/own-emoji-service.service';
import {CordovaService} from "./services/cordova.service";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

    constructor(private userProfileService: UserProfileService,
                private webSocket: WSRClientService,
                private changeService: ChangeService,
                private colorService: ColorService,
                private ownEmojiService: OwnEmojiServiceService,
                private cordovaService: CordovaService) {

    }

    title = 'mobile';

    ngOnInit() {
        // const mvp = document.getElementById('viewport');
        // if (screen.width > 400) {
        //     mvp.setAttribute('content', 'width='+screen.width+', initial-scale=1');
        // }
        // if (screen.width > 600) {
        //     mvp.setAttribute('content', 'width='+screen.width/1.5+', initial-scale=1.5');
        // }
        // if (screen.width < 400) {
        //     mvp.setAttribute('content', 'width='+screen.width/0.9+', initial-scale=0.9');
        // }
        // console.log([mvp.getAttribute("content")]);
        // alert(mvp.getAttribute("content")+ " "+screen.width)

    }
}
