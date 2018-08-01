import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule(
  {
    imports: [
      RouterModule.forRoot([
        {
          path: '',
          redirectTo: '',
          pathMatch: 'full'
        },
        {
          path: 'dashboard',
          component: DashboardComponent
        },
        {
          path: 'login',
          component: LoginFormComponent
        },
        {
          path: 'register',
          component: RegisterFormComponent
        }
      ])
    ],
    exports: [
      RouterModule
    ]
  }
)
export class AppRoutersModule {
}
