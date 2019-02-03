import {Injectable, Pipe, PipeTransform} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {EmojiService} from '@ctrl/ngx-emoji-mart/ngx-emoji';
import {OwnEmojiServiceService} from '../services/own-emoji-service.service';

@Pipe({
    name: 'emojiPipe'
})
export class EmojiPipePipe implements PipeTransform {

    constructor(private sanitizer: DomSanitizer,
                private emojiService: EmojiService,
                private ownEmojiService: OwnEmojiServiceService) {

        console.log(emojiService);
    }

    transform(value: any, args?: any): any {

        let content: string = value || '';
        const regexp = /[:][A-Za-z_/-]+[:]/g;
        const emotesNameList: Array<string> = content.match(regexp) || [];

        emotesNameList.forEach(emojiName => {


            let emoteData = this.emojiService.emojis.find(value1 => value1.colons == emojiName);
            if (emoteData != null) {
                const styles = this.emojiService.emojiSpriteStyles(
                    emoteData.sheet,
                    (this.ownEmojiService.getType()) as 'apple' | 'google' | 'twitter' | 'emojione' | 'messenger' | 'facebook' | '' ,
                    20);
                const htmlElement = document.createElement('div');
                Object.assign(htmlElement.style, styles);
                content = content.replace(emojiName, htmlElement.outerHTML);
            }

        });
        return this.sanitizer.bypassSecurityTrustHtml(content);
    }

}
