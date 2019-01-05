import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import { HomeComponent } from './pages/home/home.component';
import {AppRoutingModule} from "./app-routing/app-routing.module";
import { BackgroundWrapperComponent } from './components/background-wrapper/background-wrapper.component';

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        BackgroundWrapperComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
