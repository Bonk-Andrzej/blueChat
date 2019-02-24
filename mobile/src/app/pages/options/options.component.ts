import {Component, OnInit} from '@angular/core';
import {ColorObject} from '../../services/background/colorObject';
import {BackgroundColorService} from '../../services/background/background-color.service';
import {EmojiType, OwnEmojiServiceService} from '../../services/own-emoji-service.service';
import {FontService} from '../../services/font.service';
import {NotificationService} from '../../services/notification.service';

@Component({
    selector: 'app-options',
    templateUrl: './options.component.html',
    styleUrls: ['./options.component.scss']
})
export class OptionsComponent implements OnInit {

    public colorsList: Array<ColorObject>;
    public emojiTypeList: Array<string> = [];
    public fontSizes: Array<string> = [];

    public currentColor: ColorObject;
    public currentFont: string;
    public currentEmoji: string;

    isDisplayTheme: boolean;
    isDisplayFont: boolean;
    isDisplayEmoji: boolean;

    soundActive: Boolean;
    notificationActive: boolean;

    constructor(private bgColorService: BackgroundColorService,
                private ownEmojiService: OwnEmojiServiceService,
                private fontService: FontService,
                private notificationService: NotificationService) {

    }

    ngOnInit() {
        //color settings
        this.colorsList = this.bgColorService.colors;
        this.bgColorService.getCurrentColor().subscribe(color => {
            this.currentColor = color;
        });
        this.isDisplayTheme = false;

        //emoji settings
        for (let emojiTypeKey in  EmojiType) {
            this.emojiTypeList.push(emojiTypeKey.toLowerCase());
        }
        this.ownEmojiService.getTypeObs().subscribe(emoji => {
            this.currentEmoji = emoji;
        });
        this.isDisplayEmoji = false;


        // font settings
        this.fontService.getCurrentFontSizeObs().subscribe(fontSize => {
            this.currentFont = fontSize;
        });
        this.fontSizes = this.fontService.getFontSize();
        this.isDisplayFont = false;

        //sound notification settings

        this.soundActive = this.notificationService.getNotificationSound();
    }

    setBackground(color: ColorObject) {
        this.bgColorService.setCurrentColor(color);
    }

    setType(type: EmojiType) {
        console.log(type);
        alert('Enjoy emote type: ' + type);
        this.ownEmojiService.setTupe(type);
    }

    setFont(font: string) {
        this.fontService.setCurrentSize(font);
        alert('You choose font size ' + font);
    }

    triggerDisplayEmoji() {
        this.isDisplayEmoji = !this.isDisplayEmoji;
    }

    triggerDisplayTheme() {
        this.isDisplayTheme = !this.isDisplayTheme;
    }

    triggerDisplayFont() {
        this.isDisplayFont = !this.isDisplayFont;

    }


    setSound(isSound: boolean) {
        this.notificationService.setNotificationSound(isSound);
    }
}
