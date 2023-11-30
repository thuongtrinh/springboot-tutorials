import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './components/home/home.component';
import { RegistrationConfirmComponent } from './components/registration-confirm/registration-confirm.component';
import { UpdatePasswordComponent } from './components/update-password/update-password.component';
import { ForgetPasswordComponent } from './components/forget-password/forget-password.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { InformSuccessComponent } from './components/inform-success/inform-success.component';
import { InformErrorComponent } from './components/inform-error/inform-error.component';
import { HeaderComponent } from './components/header/header.component';
import { SharedModule } from './shared/shared/shared.module';
import { httpInterceptorProviders } from './interceptors';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HomeComponent,
    RegistrationConfirmComponent,
    UpdatePasswordComponent,
    ForgetPasswordComponent,
    InformSuccessComponent,
    InformErrorComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    SharedModule.forRoot()
  ],
  providers: [
    httpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
