import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {ChatPageComponent} from './chat-page/chat-page.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {AppRoutingModule} from './app-routing.module';
import {HeaderComponent} from './chat-page/header/header.component';
import {ConversationsComponent} from './chat-page/conversations/conversations.component';
import {ActiveUserListComponent} from './chat-page/active-user-list/active-user-list.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { CssValidatorComponent } from './css-validator/css-validator.component';


@NgModule({
    declarations: [
        AppComponent,
        ChatPageComponent,
        LoginPageComponent,
        HeaderComponent,
        ConversationsComponent,
        ActiveUserListComponent,
        CssValidatorComponent,

    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule
    ],
   providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
