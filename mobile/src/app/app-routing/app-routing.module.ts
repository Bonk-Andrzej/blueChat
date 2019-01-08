import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from "../pages/register/register.component";
import {HomeMenuComponent} from '../pages/home-menu/home-menu.component';
import {ResetPasswordComponent} from '../pages/reset-password/reset-password.component';
import {HomeComponent} from '../pages/home/home.component';
import {ContactComponent} from '../pages/contact/contact.component';
import {LogOutComponent} from '../pages/log-out/log-out.component';


const routes: Routes = [
    {path: '', component: RegisterComponent},
    {path: 'home', component: HomeComponent},
    {path: 'home-menu', component: HomeMenuComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'reset-pass', component: ResetPasswordComponent},
    {path: 'contact', component: ContactComponent},
    {path: 'log-out', component: LogOutComponent}

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
