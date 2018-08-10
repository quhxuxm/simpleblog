import {Component, OnInit} from '@angular/core';
import {ArticleSummary} from "../vo/article-summary";

@Component({
  selector: 'app-article-summaries',
  templateUrl: './article-summaries.component.html',
  styleUrls: ['./article-summaries.component.scss']
})
export class ArticleSummariesComponent implements OnInit {
  summaries: ArticleSummary[] = [];

  constructor() {
  }

  ngOnInit() {
    for (let i = 0; i < 20; i++) {
      this.summaries.push(new ArticleSummary(i,
        `Article ${i} title`,
        `Article ${i} summary`,
        new Date(),
        new Date(),
        i + 100,
        `Author ${i} nick name`, i + 200,
        i + 300, `Anthology ${i} title`,
        i + 400, 100, 100,
        100, 100, i + 500))
    }
  }

}
