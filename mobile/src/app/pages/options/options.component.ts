import {Component, OnInit} from '@angular/core';
import {ColorObject} from '../../services/background/colorObject';
import {ColorService} from '../../services/background/color.service';
import {EmojiType, OwnEmojiServiceService} from '../../services/own-emoji-service.service';
import {Observable} from 'rxjs';

@Component({
    selector: 'app-options',
    templateUrl: './options.component.html',
    styleUrls: ['./options.component.scss']
})
export class OptionsComponent implements OnInit {

    public colorsList: Array<ColorObject>;
    public emojiTypeList: Array<string> = [];
    public fontSizes: Array<string> =[];
    public currentColor: Observable<string>;
    isDisplayTheme: boolean;
    isDisplayFont: boolean;
    isDisplayEmoji: boolean;

    soundActive: boolean;
    notificationActive: boolean;
    constructor(private bgColorService: ColorService,
                private ownEmojiService: OwnEmojiServiceService) {

    }

    ngOnInit() {
        for (let emojiTypeKey in  EmojiType) {
            this.emojiTypeList.push(emojiTypeKey.toLowerCase());
        }
        this.colorsList = this.bgColorService.colors;
        this.currentColor = this.bgColorService.getCurrentColor();

        this.isDisplayEmoji = false;
        this.isDisplayFont = false;
        this.isDisplayTheme = false;

        // change for service
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
        alert('You choose font size '+ font)
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
