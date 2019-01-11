import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing/app-routing.module';
import {BackgroundWrapperComponent} from './components/background-wrapper/background-wrapper.component';
import {HamburgerMenuComponent} from './components/hamburger-menu/hamburger-menu.component';
import {BigLogoComponent} from './components/big-logo/big-logo.component';
import {InputWithTitleComponent} from './components/input-with-title/input-with-title.component';
import {FormsModule} from '@angular/forms';
import {ButtonWideComponent} from './components/button-wide/button-wide.component';
import {HomeMenuComponent} from './pages/home-menu/home-menu.component';
import {ResetPasswordComponent} from './pages/reset-password/reset-password.component';
import {InformationSupportComponent} from './components/information-support/information-support.component';
import {InputWithButtonComponent} from './components/input-with-button/input-with-button.component';
import {RegisterComponent} from './pages/register/register.component';
import { TitlePageComponent } from './components/title-page/title-page.component';
import { HomeComponent } from './pages/home/home.component';
import { ContactComponent } from './pages/contact/contact.component';
import { LogOutComponent } from './pages/log-out/log-out.component';
import { BoxLogOutComponent } from './components/box-log-out/box-log-out.component';
import { MenuBottomComponent } from './components/menu-bottom/menu-bottom.component';
import { MainLoginComponent } from './pages/main-login/main-login.component';
import { InputSearchComponent } from './components/input-search/input-search.component';
import { ListUsersWithTitleComponent } from './components/list-users-with-title/list-users-with-title.component';
import { ConversationComponent } from './pages/conversation/conversation.component';
import { LeftMenuComponent } from './components/left-menu/left-menu.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { UserWithMessageComponent } from './components/user-with-message/user-with-message.component';
import { CreateGroupComponent } from './pages/create-group/create-group.component';
import { TextareaWithTitleComponent } from './components/textarea-with-title/textarea-with-title.component';
import { GroupsComponent } from './pages/groups/groups.component';
import { ButtonPlusComponent } from './components/button-plus/button-plus.component';
import { ButtonBackComponent } from './components/button-back/button-back.component';
import { ObjectWithXComponent } from './components/object-with-x/object-with-x.component';
import { ObjectWithStatusComponent } from './components/object-with-status/object-with-status.component';
import { BoxMessageComponent } from './components/box-message/box-message.component';
import { HelperComponent } from './pages/helper/helper.component';
import { ButtonSendingComponent } from './components/button-sending/button-sending.component';
import { InputWithInsideTextComponent } from './components/input-with-inside-text/input-with-inside-text.component';
import { ObjectWithCheckboxComponent } from './components/object-with-checkbox/object-with-checkbox.component';

@NgModule({
    declarations: [
        AppComponent,
        RegisterComponent,
        BackgroundWrapperComponent,
        HamburgerMenuComponent,
        BigLogoComponent,
        InputWithTitleComponent,
        ButtonWideComponent,
        HomeMenuComponent,
        ResetPasswordComponent,
        InformationSupportComponent,
        InputWithButtonComponent,
        TitlePageComponent,
        HomeComponent,
        ContactComponent,
        LogOutComponent,
        BoxLogOutComponent,
        MenuBottomComponent,
        MainLoginComponent,
        InputSearchComponent,
        ListUsersWithTitleComponent,
        ConversationComponent,
        LeftMenuComponent,
        UserWithMessageComponent,
        CreateGroupComponent,
        TextareaWithTitleComponent,
        GroupsComponent,
        ButtonPlusComponent,
        ButtonBackComponent,
        ObjectWithXComponent,
        ObjectWithStatusComponent,
        BoxMessageComponent,
        HelperComponent,
        ButtonSendingComponent,
        InputWithInsideTextComponent,
        ObjectWithCheckboxComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        BrowserAnimationsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
