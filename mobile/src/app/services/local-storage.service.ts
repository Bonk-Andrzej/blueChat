import {Injectable} from '@angular/core';


@Injectable({
    providedIn: 'root'
})
export class LocalStorageService {

    constructor() {
    }

    public saveObject<OBJECT>(name: string, object: OBJECT) {
        localStorage.setItem(name, JSON.stringify(object))
    }

    public fetchObject<OBJECT>(name: string, object: new () => OBJECT): OBJECT {

        if (typeof object == "object") {
            return Object.assign(object, JSON.parse(localStorage.getItem(name)));
        } else {
            return Object.assign(new object(), JSON.parse(localStorage.getItem(name)));
        }
    }

    public hasObject(key: string): boolean {
        return !!localStorage.getItem(key);
    }

    public removeObject(key: string): void {
        localStorage.removeItem(key);
    }
}
