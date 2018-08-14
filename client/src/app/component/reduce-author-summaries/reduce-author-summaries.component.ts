import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorIntl} from '@angular/material';
import {AuthorSummary} from '../../vo/author-summary';

@Component({
  selector: 'app-reduce-author-summaries',
  templateUrl: './reduce-author-summaries.component.html',
  styleUrls: ['./reduce-author-summaries.component.scss']
})
export class ReduceAuthorSummariesComponent implements OnInit {
  @Input()
  authorSummaries: AuthorSummary[];
  @Input()
  totalNumber: number;
  @Input()
  pageSize: number;
  @ViewChild(MatPaginator)
  paginator: MatPaginator;

  constructor() {
    this.authorSummaries = [];
    this.totalNumber = 0;
    this.pageSize = 0;
  }

  ngOnInit() {
    const paginatorIntl = new MatPaginatorIntl();
    paginatorIntl.previousPageLabel = '上一页';
    paginatorIntl.nextPageLabel = '下一页';
    this.paginator._intl = paginatorIntl;
    this.authorSummaries = ReduceAuthorSummariesComponent.generateAuthorSummaries();
    this.totalNumber = this.authorSummaries.length;
  }

  private static generateAuthorSummaries(): AuthorSummary[] {
    const result: AuthorSummary[] = [];
    for (let i = 0; i < 20; i++) {
      let summary = new AuthorSummary();
      summary.id = i;
      summary.nickName = '测试用户' + i;
      summary.description = '测试用户描述' + i;
      summary.lastLoginDate = new Date();
      summary.followerNumber = 10000;
      summary.iconImageId = i;
      result.push(summary);
    }
    return result;
  }
}
