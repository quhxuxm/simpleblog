import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppRoutersModule } from './app.routers.module';
import { AppMaterialModule } from './app.material.module';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { AuthenticationService } from './service/impl/AuthenticationService';
import { ConnectionService } from './service/impl/ConnectionService';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DataService } from './data/data.service';

export function createHttpTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegisterFormComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutersModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: createHttpTranslateLoader,
        deps: [HttpClient]
      }
    })
  ],
  providers: [{
    provide: 'connectionService',
    useClass: ConnectionService
  }, {
    provide: 'authenticationService',
    useClass: AuthenticationService
  }, {
    provide: 'dataService',
    useClass: DataService
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
