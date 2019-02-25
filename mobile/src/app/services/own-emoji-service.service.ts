import {EventEmitter, Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {EmojiData} from '@ctrl/ngx-emoji-mart/ngx-emoji';

@Injectable({
    providedIn: 'root'
})
export class OwnEmojiServiceService {


    private cuttentType: BehaviorSubject<EmojiType>;
    private currentQuality: BehaviorSubject<string>;
    public onSelectetEmoji = new EventEmitter<string>();

    constructor() {
        this.cuttentType = new BehaviorSubject<EmojiType>(EmojiType.TWITTER);
        this.currentQuality = new BehaviorSubject<string>('LOW')
    }

    //type
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
    //quality
    public setQuality(quality: string){
        this.currentQuality.next(quality);
    }
    public getQualityObs(): Observable<string>{
        return this.currentQuality.asObservable();
    }
    public getQuality() : EmojiQuality {
        console.error(this.currentQuality.getValue())
        return EmojiQuality[this.currentQuality.getValue()];
    }


}

export enum EmojiType {

    GOOGLE = 'google',
    APPLE = 'apple',
    TWITTER = 'twitter',
    EMOJIONE = 'emojione',
    MESSAGER = 'messenger',
    FACEBOOK = 'facebook',

}

export enum EmojiQuality {
    POOR= 16,
    LOW = 20,
    GOOD = 32,
}
