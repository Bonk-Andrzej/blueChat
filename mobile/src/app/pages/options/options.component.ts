import {Component, OnInit} from '@angular/core';
import {ColorObject} from '../../services/background/colorObject';
import {BackgroundColorService} from '../../services/background/background-color.service';
import {EmojiType, OwnEmojiServiceService} from '../../services/own-emoji-service.service';

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
    public currentEmoji :string;

    isDisplayTheme: boolean;
    isDisplayFont: boolean;
    isDisplayEmoji: boolean;

    soundActive: boolean;
    notificationActive: boolean;

    constructor(private bgColorService: BackgroundColorService,
                private ownEmojiService: OwnEmojiServiceService) {

    }

    ngOnInit() {
        for (let emojiTypeKey in  EmojiType) {
            this.emojiTypeList.push(emojiTypeKey.toLowerCase());
        }

        //color settings
        this.colorsList = this.bgColorService.colors;
        this.bgColorService.getCurrentColor().subscribe(color => {
            this.currentColor = color;
        });
        this.isDisplayTheme = false;

        //emoji settings
        this.ownEmojiService.getTypeObs().subscribe(emoji => {
            this.currentEmoji = emoji.toString();
        });
        this.isDisplayEmoji = false;


        // change for service
        this.currentFont = '14';
        this.isDisplayFont = false;
        this.fontSizes.push('14');
        this.fontSizes.push('15');
        this.fontSizes.push('16');
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


}
