import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {LoginFormComponent} from './component/login-form/login-form.component';
import {RegisterFormComponent} from './component/register-form/register-form.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ConnectionService} from "./service/impl/ConnectionService";
import {AuthenticationService} from "./service/impl/AuthenticationService";
import {ArticleSummaryModule} from "./component/article-summary/article-summary.module";
import {AppRoutersModule} from "./app.routers";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule, MatCheckboxModule} from "@angular/material";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core"
import {TranslateHttpLoader} from "@ngx-translate/http-loader";

export function createHttpTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, "./assets/i18n/", ".json")
}

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegisterFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ArticleSummaryModule,
    AppRoutersModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: createHttpTranslateLoader,
        deps: [HttpClient]
      }
    })
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
