import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from 'src/app/services/Auth.service';
import { ModuleWithProviders } from '@angular/compiler/src/core';
import { AuthGuardService } from 'src/app/services/auth-guard.service';

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class SharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SharedModule,
      providers: [AuthService, AuthGuardService]
    };
  }
}
