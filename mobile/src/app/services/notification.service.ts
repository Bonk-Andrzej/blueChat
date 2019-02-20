import {EventEmitter, Injectable, NgZone} from '@angular/core';
import {MessageDto} from "../repository/message/messageDto";
import {Router} from "@angular/router";
import {LocalStorageService} from "./local-storage.service";
import {RetrieveStateApplicationService} from "./retrieve-state-application.service";

@Injectable({
    providedIn: 'root'
})
export class NotificationService {

    private readonly MESSAGENOTIFICATIONKEY: string = "Notification";
    public onGoToConversation = new EventEmitter<MessageDto>()

    constructor(private zone: NgZone,
                private router: Router,
                private storage: LocalStorageService,
                public  appState: RetrieveStateApplicationService) {

        this.addClickEventsToNotification();
    }

    private addClickEventsToNotification() {
        document.addEventListener('deviceready', () => {
            window.cordova.plugins.notification.local.on('click', () => {

                this.appState.onRetrieveApplicationState.subscribe(user => {
                    if (user != null && this.storage.hasObject(this.MESSAGENOTIFICATIONKEY)) {
                        let messageDto = this.storage.fetchObject(this.MESSAGENOTIFICATIONKEY, MessageDto);
                        this.onGoToConversation.emit(messageDto);
                        this.storage.removeObject(this.MESSAGENOTIFICATIONKEY);
                    }
                })

            });
            window.cordova.plugins.notification.local.on('cancel', () => {
                this.storage.removeObject(this.MESSAGENOTIFICATIONKEY);
            });
            window.cordova.plugins.notification.local.on('clear', () => {
                this.storage.removeObject(this.MESSAGENOTIFICATIONKEY);
            });
        });
    }

    public notifyNewMessage(message: MessageDto) {

        window.cordova.plugins.notification.local.schedule({
            title: "Text Jet - " + message.sender.nick,
            text: "New Message: " + message.content,
            foreground: true,
            smallIcon: 'res://icon',
            icon: 'res://icon'
        });
        window.cordova.plugins.notification.local.on('click', () => {
            this.zone.run(() => {
                this.onGoToConversation.emit(message);
            }, false);
            this.storage.removeObject(this.MESSAGENOTIFICATIONKEY);

        });
        this.storage.saveObject(this.MESSAGENOTIFICATIONKEY, message);
        navigator.notification.beep(1);

    }

    public notifiNewChannelMessage() {
    }
}
