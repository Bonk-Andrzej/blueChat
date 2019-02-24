import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class FontService {

    fontSizeList = new Array<string>();
    private currentFontSize: BehaviorSubject<string> = new BehaviorSubject('');

    constructor() {
        this.fontSizeList.push('10');
        this.fontSizeList.push('11');
        this.fontSizeList.push('12');
        this.fontSizeList.push('13');

       this.setCurrentSize('10');
    }



    public getCurrentFontSizeObs() : Observable<string>{
        return this.currentFontSize.asObservable();
    }

    public getFontSize(): Array<string>{
        return this.fontSizeList;
    }

    public setCurrentSize(fontSize: string){
        this.currentFontSize.next(fontSize);
        document.body.style.fontSize = fontSize + 'px';
    }
}
