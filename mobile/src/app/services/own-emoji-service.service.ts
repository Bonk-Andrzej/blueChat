import {EventEmitter, Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {EmojiData} from '@ctrl/ngx-emoji-mart/ngx-emoji';

@Injectable({
    providedIn: 'root'
})
export class OwnEmojiServiceService {


    private cuttentType: BehaviorSubject<EmojiType>;
    public onSelectetEmoji = new EventEmitter<string>();

    constructor() {
        this.cuttentType = new BehaviorSubject<EmojiType>(EmojiType.TWITTER);

    }

    public pickEmoji(emojiData: EmojiData) {
        this.onSelectetEmoji.emit(emojiData.colons);
    }

    public setTupe(type: EmojiType) {
        this.cuttentType.next(type);
    }
    public getTypeObs() : Observable<EmojiType>{
        return this.cuttentType.asObservable()
    }
    public getType() : EmojiType{
        return this.cuttentType.getValue()
    }


}

export enum EmojiType {

    GOOGLE = 'google',
    APPLE = 'apple',
    TWITTER = 'twitter',
    EMOJIONE = 'emojione',
    // MESSAGER = 'messenger',
    FACEBOOK = 'facebook',

}
