import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SplashScreenService {

  private readonly delayHide: number = 200;
  constructor() { }

  public hide(){
      try{
        setTimeout(()=>{
            navigator.splashscreen.hide();
        },this.delayHide)
      }catch (e) {}
  }
}
