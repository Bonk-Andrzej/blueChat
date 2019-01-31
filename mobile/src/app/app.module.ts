import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { BackgroundWrapperComponent } from './components/background-wrapper/background-wrapper.component';
import { HamburgerMenuComponent } from './components/hamburger-menu/hamburger-menu.component';
import { BigLogoComponent } from './components/big-logo/big-logo.component';
import { InputWithTitleComponent } from './components/input-with-title/input-with-title.component';
import { FormsModule } from '@angular/forms';
import { ButtonWideComponent } from './components/button-wide/button-wide.component';
import { HomeMenuComponent } from './pages/home-menu/home-menu.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { InformationSupportComponent } from './components/information-support/information-support.component';
import { InputWithButtonComponent } from './components/input-with-button/input-with-button.component';
import { RegisterComponent } from './pages/register/register.component';
import { TitleComponent } from './components/title/title.component';
import { HomeComponent } from './pages/home/home.component';
import { ContactComponent } from './pages/contact/contact.component';
import { LogOutComponent } from './pages/log-out/log-out.component';
import { ConfirmBoxComponent } from './components/confirm-box/confirm-box.component';
import { MenuBottomComponent } from './components/menu-bottom/menu-bottom.component';
import { MainLoginComponent } from './pages/main-login/main-login.component';
import { InputSearchComponent } from './components/input-search/input-search.component';
import { ListUsersWithTitleComponent } from './components/list-users-with-title/list-users-with-title.component';
import { ConversationComponent } from './pages/conversation/conversation.component';
import { LeftMenuComponent } from './components/left-menu/left-menu.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
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
import { BoxProfilesComponent } from './components/box-profiles/box-profiles.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { ButtonAddFriendComponent } from './components/button-add-friend/button-add-friend.component';
import { FriendsComponent } from './pages/friends/friends.component';
import { SearchComponent } from './pages/search/search.component';
import { OptionsComponent } from './pages/options/options.component';
import { GroupProfileComponent } from './pages/group-profile/group-profile.component';
import { EmojifyModule } from 'angular-emojify';
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
        TitleComponent,
        HomeComponent,
        ContactComponent,
        LogOutComponent,
        ConfirmBoxComponent,
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
        BoxProfilesComponent,
        ProfileComponent,
        UserProfileComponent,
        ButtonAddFriendComponent,
        FriendsComponent,
        SearchComponent,
        OptionsComponent,
        GroupProfileComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        BrowserAnimationsModule,
        HttpClientModule,
        EmojifyModule

    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
