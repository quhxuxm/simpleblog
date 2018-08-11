import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AnthologyDetailComponent} from './component/anthology-detail/anthology-detail.component';
import {ArticleDetailComponent} from './component/article-detail/article-detail.component';
import {AuthorDetailComponent} from './component/author-detail/author-detail.component';
import {HomeComponent} from './component/home/home.component';
import {LoginComponent} from './component/login/login.component';
import {RegisterComponent} from './component/register/register.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'article/:id',
    component: ArticleDetailComponent
  },
  {
    path: 'anthology/:id',
    component: AnthologyDetailComponent
  },
  {
    path: 'author/:id',
    component: AuthorDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
