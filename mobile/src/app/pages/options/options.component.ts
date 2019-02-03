import {Component, OnInit} from '@angular/core';
import {ColorObject} from '../../services/background/colorObject';
import {ColorService} from '../../services/background/color.service';
import {EmojiType, OwnEmojiServiceService} from '../../services/own-emoji-service.service';

@Component({
    selector: 'app-options',
    templateUrl: './options.component.html',
    styleUrls: ['./options.component.scss']
})
export class OptionsComponent implements OnInit {

    public colorsList: Array<ColorObject>;
    public emojiTypeList : Array<string> = [];

    constructor(private bgColorService: ColorService,
                private ownEmojiService: OwnEmojiServiceService) {

    }

    ngOnInit() {
        for (let emojiTypeKey in  EmojiType) {
            this.emojiTypeList.push(emojiTypeKey.toLowerCase())
        }
        this.colorsList = this.bgColorService.colors;
    }

    setBackground(color: ColorObject){
        this.bgColorService.setCurrentColor(color);
    }
    setType(type : EmojiType){
        console.log(type);
        this.ownEmojiService.setTupe(type)
    }

}
