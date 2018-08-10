import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AppUiModule} from './app.ui.module';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {NavigatorComponent} from './navigator/navigator.component';
import {RegisterComponent} from './register/register.component';
import { ArticleSummariesComponent } from './article-summaries/article-summaries.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigatorComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ArticleSummariesComponent
  ],
  imports: [
    AppRoutingModule,
    AppUiModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
