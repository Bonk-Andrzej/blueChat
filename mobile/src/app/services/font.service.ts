import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class FontService {

    fontSizeList = new Array<string>();
    private currentFontSize: BehaviorSubject<string> = new BehaviorSubject('');

    constructor() {
        this.fontSizeList.push('14');
        this.fontSizeList.push('15');
        this.fontSizeList.push('16');

       this.setCurrentFontSize();
    }

    setCurrentFontSize() {
        this.currentFontSize.next('14')
    }

    public getCurrentFontSizeObs() : Observable<string>{
        return this.currentFontSize.asObservable();
    }

    public getFontSize(): Array<string>{
        return this.fontSizeList;
    }

    public setCurrentSize(fontSize: string){
        this.currentFontSize.next(fontSize);
    }
}
