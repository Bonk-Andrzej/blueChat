import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import { HomeComponent } from './pages/home/home.component';
import {AppRoutingModule} from "./app-routing/app-routing.module";
import { BackgroundWrapperComponent } from './components/background-wrapper/background-wrapper.component';
import { HamburgerMenuComponent } from './components/hamburger-menu/hamburger-menu.component';
import { BigLogoComponent } from './components/big-logo/big-logo.component';
import { InputWithTitleComponent } from './components/input-with-title/input-with-title.component';
import { FormsModule }   from '@angular/forms';
import { ButtonWideComponent } from './components/button-wide/button-wide.component';
import { HomeMenuComponent } from './pages/home-menu/home-menu.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { InformationSupportComponent } from './components/information-support/information-support.component';
import { InputWithButtonComponent } from './components/input-with-button/input-with-button.component';

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        BackgroundWrapperComponent,
        HamburgerMenuComponent,
        BigLogoComponent,
        InputWithTitleComponent,
        ButtonWideComponent,
        HomeMenuComponent,
        ResetPasswordComponent,
        InformationSupportComponent,
        InputWithButtonComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
