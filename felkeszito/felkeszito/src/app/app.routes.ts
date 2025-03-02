import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { TestComponent } from './test/test.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';



export const routes: Routes = [
    {path:'', redirectTo:'/register', pathMatch:'full'},
    {path: 'register', component:RegisterComponent},
    {path: 'login', component:LoginComponent},
    {path: 'home', component:HomeComponent},
    {path: 'test', component:TestComponent},
    {path: 'forgot-password', component:ForgotPasswordComponent},



];


