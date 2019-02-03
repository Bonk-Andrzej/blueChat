import {Pipe, PipeTransform} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';

@Pipe({
    name: 'emojiPipe'
})
export class EmojiPipePipe implements PipeTransform {

    constructor(private sanitizer: DomSanitizer) {
    }

    transform(value: any, args?: any): any {
        if (value != null) {

            console.log('fdddd', value);

            let content: string = value;
            var regexp = /[:][A-Za-z_]+[:]/g;
            var emote: Array<string> = content.match(regexp);


            if (emote != null) {
                emote.forEach(emoji => {
                    let emojiContent = emoji.substr(1, emoji.length - 2);
                    console.log(emojiContent);
                    let emojiHtml = `<ngx-emoji emoji='${emojiContent}' set='emojione' size='16'></ngx-emoji>`;
                    console.log('>>>>>>>>>>>>>>>>>>',emojiHtml);
                    content = content.replace(emoji, emojiHtml);
                });
            }


            var safeHtml = this.sanitizer.bypassSecurityTrustHtml(content);


            return safeHtml;
        } else {
            return (value);
        }

    }

}
