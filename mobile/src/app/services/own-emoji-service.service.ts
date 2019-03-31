import {EventEmitter, Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {EmojiData} from '@ctrl/ngx-emoji-mart/ngx-emoji';
import {LocalStorageService} from './local-storage.service';

@Injectable({
    providedIn: 'root'
})
export class OwnEmojiServiceService {

    private readonly STORAGEEMOJITYPE = 'StorageEmojiType';
    private readonly STOGRAGEEMOJIQUALITY = 'StorageEmojiQuality';
    private cuttentType : BehaviorSubject<EmojiType>;
    private currentQuality: BehaviorSubject<string>;
    public onSelectetEmoji = new EventEmitter<string>();

    constructor(private storage: LocalStorageService) {
        this.cuttentType = new BehaviorSubject<EmojiType>(EmojiType.TWITTER);
        this.currentQuality = new BehaviorSubject<string>('LOW')
        // this.checkStorageQuality();
        // this.checkStorageType();
    }

    private checkStorageType() {
        if (this.storage.hasObject(this.STORAGEEMOJITYPE)) {
            let storageType = this.storage.fetchObject(this.STORAGEEMOJITYPE, String);
            this.cuttentType.next(EmojiType[storageType.valueOf()]);
            this.storage.saveObject(this.STORAGEEMOJITYPE, storageType);
        } else {
            this.cuttentType.next(EmojiType.TWITTER);
        }


    }

    private checkStorageQuality() {
        if (this.storage.hasObject(this.STOGRAGEEMOJIQUALITY)) {
            let quality = this.storage.fetchObject(this.STOGRAGEEMOJIQUALITY, String);

            let stringObject = new String(quality);
            let stringPrimitive = stringObject.valueOf();

            this.currentQuality.next(stringPrimitive);
            this.storage.saveObject(this.STOGRAGEEMOJIQUALITY, stringObject);
        } else {
            this.currentQuality.next('LOW');
        }
    }

    //type
    public pickEmoji(emojiData: EmojiData) {
        this.onSelectetEmoji.emit(emojiData.colons);
    }

    public setTupe(type: EmojiType) {
        this.cuttentType.next(type);
    }

    public getTypeObs(): Observable<EmojiType> {
        return this.cuttentType.asObservable();
    }

    public getType(): EmojiType {
        return this.cuttentType.getValue();
    }

    //quality
    public setQuality(quality: string) {
        this.currentQuality.next(quality);
    }

    public getQualityObs(): Observable<string> {
        return this.currentQuality.asObservable();
    }

    public getQuality(): EmojiQuality {
        console.error(this.currentQuality.getValue());
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
    POOR = 16,
    LOW = 20,
    GOOD = 32,
    HIGH = 64,
}
