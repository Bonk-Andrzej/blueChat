import { Component, OnInit } from '@angular/core';
import {EmojiEvent} from '@ctrl/ngx-emoji-mart/ngx-emoji';


@Component({
  selector: 'app-own-emoji',
  templateUrl: './own-emoji.component.html',
  styleUrls: ['./own-emoji.component.scss']
})
export class OwnEmojiComponent implements OnInit {

    themes = [
        'native',
        'apple',
        'google',
        'twitter',
        'emojione',
        'messenger',
        'facebook',
    ];
    set = 'apple';
    native = true;
    CUSTOM_EMOJIS = OwnEmojiComponent;

    setTheme(set: string) {
        this.native = set === 'apple';
        this.set = set;
    }
    handleClick($event: EmojiEvent) {
        console.log($event.emoji);
    }
    constructor() { }

  ngOnInit() {
  }

}
