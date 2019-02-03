import {EventEmitter, Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {EmojiData} from '@ctrl/ngx-emoji-mart/ngx-emoji';

@Injectable({
    providedIn: 'root'
})
export class OwnEmojiServiceService {



    private cuttentType: Observable<string>;
    public onSelectetEmoji = new EventEmitter<string>();

    constructor() {

    }
    public pickEmoji(emojiData: EmojiData) {
        this.onSelectetEmoji.emit(emojiData.colons)
    }

}
