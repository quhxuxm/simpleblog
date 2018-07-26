import {CommonModule} from "@angular/common";
import {ArticleSummaryComponent} from "./article-summary.component";
import {ArticleSummaryFooterComponent} from "./article-summary-footer/article-summary-footer.component";
import {ArticleSummaryHeaderComponent} from "./article-summary-header/article-summary-header.component";
import {ArticleSummaryBodyComponent} from "./article-summary-body/article-summary-body.component";
import {NgModule} from "@angular/core";
import { ArticleSummaryCoverComponent } from './article-summary-cover/article-summary-cover.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    ArticleSummaryComponent,
    ArticleSummaryFooterComponent,
    ArticleSummaryHeaderComponent,
    ArticleSummaryBodyComponent,
    ArticleSummaryCoverComponent
  ],
  providers: []
})
export class ArticleSummaryModule {
}
