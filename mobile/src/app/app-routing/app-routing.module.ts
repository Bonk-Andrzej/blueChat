import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from "../pages/register/register.component";
import {HomeMenuComponent} from '../pages/home-menu/home-menu.component';
import {ResetPasswordComponent} from '../pages/reset-password/reset-password.component';
import {HomeComponent} from '../pages/home/home.component';
import {ContactComponent} from '../pages/contact/contact.component';
import {LogOutComponent} from '../pages/log-out/log-out.component';
import {MainLoginComponent} from '../pages/main-login/main-login.component';
import {ConversationComponent} from '../pages/conversation/conversation.component';
import {CreateGroupComponent} from '../pages/create-group/create-group.component';
import {GroupsComponent} from '../pages/groups/groups.component';
import {HelperComponent} from '../pages/helper/helper.component';


const routes: Routes = [
    // {path: '', component: HomeComponent},
    {path: 'home', component: HomeComponent},
    {path: 'home-menu', component: HomeMenuComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'reset-pass', component: ResetPasswordComponent},
    {path: 'contact', component: ContactComponent},
    {path: 'log-out', component: LogOutComponent},
    {path: 'main-login', component: MainLoginComponent},
    {path: 'conversation', component: ConversationComponent},
    {path: 'create-group', component: CreateGroupComponent},
    // {path: '', component: ConversationComponent},
    {path: '', component: HelperComponent},

];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ],
    declarations: []
})
export class AppRoutingModule {
}
