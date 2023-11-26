import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from '../components/registration/registration.component';
import { LoginComponent } from '../components/login/login.component';
import { RegistrationConfirmComponent } from '../components/registration-confirm/registration-confirm.component';
import { ForgetPasswordComponent } from '../components/forget-password/forget-password.component';
import { UpdatePasswordComponent } from '../components/update-password/update-password.component';
import { HomeComponent } from '../components/home/home.component';
import { InformSuccessComponent } from '../components/inform-success/inform-success.component';
import { InformErrorComponent } from '../components/inform-error/inform-error.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registration-confirm',
    component: RegistrationConfirmComponent
  },
  {
    path: 'forget-password',
    component: ForgetPasswordComponent
  },
  {
    path: 'update-password',
    component: UpdatePasswordComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'inform-success/:id',
    component: InformSuccessComponent
  },
  {
    path: 'inform-error',
    component: InformErrorComponent
  }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes, {
      preloadingStrategy: PreloadAllModules
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
