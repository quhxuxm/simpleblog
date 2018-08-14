import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AppUiModule} from './app.ui.module';
import {AnthologyDetailComponent} from './component/anthology-detail/anthology-detail.component';
import {AnthologySummariesComponent} from './component/anthology-summaries/anthology-summaries.component';
import {AnthologySummaryComponent} from './component/anthology-summary/anthology-summary.component';
import {ArticleDetailComponent} from './component/article-detail/article-detail.component';
import {ArticleSummariesComponent} from './component/article-summaries/article-summaries.component';
import {ArticleSummaryComponent} from './component/article-summary/article-summary.component';
import {AuthorDetailComponent} from './component/author-detail/author-detail.component';
import {HomeComponent} from './component/home/home.component';
import {LoginComponent} from './component/login/login.component';
import {NavigatorComponent} from './component/navigator/navigator.component';
import {RegisterComponent} from './component/register/register.component';
import { ReduceArticleSummariesComponent } from './component/reduce-article-summaries/reduce-article-summaries.component';
import { BannerComponent } from './component/banner/banner.component';

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
    AuthorDetailComponent,
    ReduceArticleSummariesComponent,
    BannerComponent
  ],
  imports: [
    AppRoutingModule,
    AppUiModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
