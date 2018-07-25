import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {ArticleSummaryComponent} from './component/article-summary/article-summary.component';
import {LoginFormComponent} from './component/login-form/login-form.component';
import {RegisterFormComponent} from './component/register-form/register-form.component';
import {RouterModule, Routes} from "@angular/router";
import {ArticleDetailComponent} from './component/article-detail/article-detail.component';
import {HttpClientModule} from "@angular/common/http";
import {ConnectionService} from "./service/ConnectionService";
import {AuthenticationService} from "./service/AuthenticationService";

export const ROUTES: Routes = [
  {path: 'register', component: RegisterFormComponent},
  {path: 'login', component: LoginFormComponent},
  {path: 'article/detail/:id', component: ArticleDetailComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    ArticleSummaryComponent,
    LoginFormComponent,
    RegisterFormComponent,
    ArticleDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(ROUTES),
    HttpClientModule
  ],
  providers: [{
    provide: "connectionService",
    useClass: ConnectionService
  }, {
    provide: "authenticationService",
    useClass: AuthenticationService
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
