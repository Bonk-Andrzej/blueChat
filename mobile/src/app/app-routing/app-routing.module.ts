import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from "../pages/register/register.component";
import {HomeMenuComponent} from '../pages/home-menu/home-menu.component';
import {ResetPasswordComponent} from '../pages/reset-password/reset-password.component';


const routes: Routes = [
    // {path: '', component: RegisterComponent},
    // {path: '', component: RegisterComponent},
    // {path: 'register-menu', component: HomeMenuComponent},
    {path: '', component: ResetPasswordComponent}
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
