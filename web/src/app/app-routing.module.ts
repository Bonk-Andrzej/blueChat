import {NgModule} from '@angular/core';

import {Routes, RouterModule} from '@angular/router';
import {ChatPageComponent} from './chat-page/chat-page.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {CssValidatorComponent} from "./css-validator/css-validator.component";



const routes: Routes = [
    {path: '', component: LoginPageComponent},
    {path: 'css', component: CssValidatorComponent },
    {path: 'login', component: LoginPageComponent},
    {path: 'chat', component: ChatPageComponent},

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
    declarations: []
})
export class AppRoutingModule {
}
