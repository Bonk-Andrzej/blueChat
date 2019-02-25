import {Component, OnInit} from '@angular/core';
import {ColorObject} from '../../services/background/colorObject';
import {BackgroundColorService} from '../../services/background/background-color.service';
import {EmojiQuality, EmojiType, OwnEmojiServiceService} from '../../services/own-emoji-service.service';
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
    public emojiQualityList: Array<string> = [];
    public fontSizes: Array<string> = [];

    public currentColor: ColorObject;
    public currentFont: string;
    public currentEmoji: string;
    public currentQuality: string;

    isDisplayTheme: boolean;
    isDisplayFont: boolean;
    isDisplayEmoji: boolean;
    isDisplayEmojiQuality: boolean;

    soundActive: Boolean;
    isMessageActive: Boolean;

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


        //emoji quality settings


        this.emojiQualityList = Object.values(EmojiQuality).filter(x => typeof x === 'string');

        this.ownEmojiService.getQualityObs().subscribe(quality => {
            this.currentQuality = quality.toLowerCase();
        });
        this.isDisplayEmojiQuality = false;


        // font settings
        this.fontService.getCurrentFontSizeObs().subscribe(fontSize => {
            this.currentFont = fontSize;
        });
        this.fontSizes = this.fontService.getFontSize();
        this.isDisplayFont = false;

        //sound notification settings
        this.soundActive = this.notificationService.getNotificationSound();
        this.isMessageActive = this.notificationService.getNotificationMessage();

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

    setQuality(quality: string) {
        console.warn(quality);
        this.ownEmojiService.setQuality(quality);
    }

    triggerDisplayEmoji() {
        this.isDisplayEmoji = !this.isDisplayEmoji;
    }

    triggerDisplayQuality() {
        this.isDisplayEmojiQuality = !this.isDisplayEmojiQuality;
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

    setMsgNotifi(isMessageActive: boolean) {
        this.notificationService.setNotificationMessage(isMessageActive);
    }
}
