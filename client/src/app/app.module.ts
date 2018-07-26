import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {ArticleSummaryComponent} from './component/article-summary/article-summary.component';
import {LoginFormComponent} from './component/login-form/login-form.component';
import {RegisterFormComponent} from './component/register-form/register-form.component';
import {ArticleDetailComponent} from './component/article-detail/article-detail.component';
import {HttpClientModule} from "@angular/common/http";
import {ConnectionService} from "./service/impl/ConnectionService";
import {AuthenticationService} from "./service/impl/AuthenticationService";
import {RouterModule} from "@angular/router";
import { ArticleSummaryHeaderComponent } from './component/article-summary/article-summary-header/article-summary-header.component';
import { ArticleSummaryFooterComponent } from './component/article-summary/article-summary-footer/article-summary-footer.component';
import { ArticleSummaryBodyComponent } from './component/article-summary/article-summary-body/article-summary-body.component';

@NgModule({
  declarations: [
    AppComponent,
    ArticleSummaryComponent,
    LoginFormComponent,
    RegisterFormComponent,
    ArticleDetailComponent,
    ArticleSummaryHeaderComponent,
    ArticleSummaryFooterComponent,
    ArticleSummaryBodyComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot([
      {path: 'register', component: RegisterFormComponent},
      {path: 'login', component: LoginFormComponent},
      {path: 'article/detail/:id', component: ArticleDetailComponent}
    ]),
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
