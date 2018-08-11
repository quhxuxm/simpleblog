import {Component, Input, OnInit} from '@angular/core';
import {ArticleComment} from '../../vo/article-comment';
import {ArticleSummary} from '../../vo/article-summary';

@Component({
  selector: 'app-article-summary',
  templateUrl: './article-summary.component.html',
  styleUrls: ['./article-summary.component.scss']
})
export class ArticleSummaryComponent implements OnInit {
  recentComment: ArticleComment;
  @Input()
  summary: ArticleSummary;

  constructor() {
  }

  ngOnInit() {
    this.recentComment = new ArticleComment();
    this.recentComment.content = 'Article comment 1';
    this.recentComment.authorNickname = 'Author 2 nicke name';
    this.recentComment.createDate = new Date();
    this.recentComment.authorIconId = 1;
  }
}
