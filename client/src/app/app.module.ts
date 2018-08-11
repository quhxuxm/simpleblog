import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AppUiModule} from './app.ui.module';
import {HomeComponent} from './component/home/home.component';
import {LoginComponent} from './component/login/login.component';
import {NavigatorComponent} from './component/navigator/navigator.component';
import {RegisterComponent} from './component/register/register.component';
import { ArticleSummariesComponent } from './component/article-summaries/article-summaries.component';
import { ArticleSummaryComponent } from './component/article-summary/article-summary.component';
import { ArticleDetailComponent } from './component/article-detail/article-detail.component';
import { AnthologyDetailComponent } from './component/anthology-detail/anthology-detail.component';
import { AnthologySummaryComponent } from './component/anthology-summary/anthology-summary.component';
import { AnthologySummariesComponent } from './component/anthology-summaries/anthology-summaries.component';
import { AuthorDetailComponent } from './component/author-detail/author-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigatorComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ArticleSummariesComponent,
    ArticleSummaryComponent,
    ArticleDetailComponent,
    AnthologyDetailComponent,
    AnthologySummaryComponent,
    AnthologySummariesComponent,
    AuthorDetailComponent
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
