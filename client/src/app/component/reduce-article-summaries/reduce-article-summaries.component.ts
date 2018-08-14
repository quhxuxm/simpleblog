import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorIntl} from '@angular/material';
import {ArticleSummary} from '../../vo/article-summary';

@Component({
  selector: 'app-reduce-article-summaries',
  templateUrl: './reduce-article-summaries.component.html',
  styleUrls: ['./reduce-article-summaries.component.scss']
})
export class ReduceArticleSummariesComponent implements OnInit {
  @Input()
  articleSummaries: ArticleSummary[];
  @Input()
  totalNumber: number;
  @Input()
  pageSize: number;
  @ViewChild(MatPaginator)
  paginator: MatPaginator;

  constructor() {
    this.articleSummaries = [];
    this.totalNumber = 0;
    this.pageSize = 0;
  }

  ngOnInit() {
    const paginatorIntl = new MatPaginatorIntl();
    paginatorIntl.previousPageLabel = '上一页';
    paginatorIntl.nextPageLabel = '下一页';
    this.paginator._intl = paginatorIntl;
  }
}
