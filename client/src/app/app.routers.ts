import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {LoginFormComponent} from "./component/login-form/login-form.component";
import {RegisterFormComponent} from "./component/register-form/register-form.component";

@NgModule(
  {
    imports: [
      RouterModule.forRoot([
        {
          path: "login",
          component: LoginFormComponent
        },
        {
          path: "register",
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
