import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ArticleSummaryService} from '../../service/article-summary.service';
import {ArticleSummary} from '../../vo/article-summary';

@Component({
  selector: 'app-article-summaries',
  templateUrl: './article-summaries.component.html',
  styleUrls: ['./article-summaries.component.scss']
})
export class ArticleSummariesComponent implements OnInit {
  private BREAKPOINT_COLUMN_NUMBER: Map<string[], number> = new Map([
    [[Breakpoints.XSmall, Breakpoints.Small], 1],
    [[Breakpoints.Medium], 2],
    [[Breakpoints.Large, Breakpoints.XLarge], 3]
  ]);
  @Input()
  maxColumnNumber: number;
  @Input()
  summaries: ArticleSummary[];
  @Input()
  category: string;
  columnNumber: number;
  summaryColumns: ArticleSummary[][];
  @Input()
  targetLabels: string[];

  constructor(private breakpointObserver: BreakpointObserver,
              private articleSummaryService: ArticleSummaryService,
              private activateRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.prepareSummaries();
    if (!this.maxColumnNumber || this.maxColumnNumber > 3 || this.maxColumnNumber <= 0) {
      this.maxColumnNumber = 3;
    }
    this.columnNumber = this.maxColumnNumber;
    this.BREAKPOINT_COLUMN_NUMBER.forEach((v, k, m) => {
      this.breakpointObserver.observe(k)
        .subscribe(
          () => {
            if (this.breakpointObserver.isMatched(k)) {
              this.columnNumber = v;
              if (this.columnNumber > this.maxColumnNumber) {
                this.columnNumber = this.maxColumnNumber;
              }
              this.refreshSummaryColumns();
            }
          }
        );
    });
  }

  private refreshSummaryColumns(): void {
    this.summaryColumns = [];
    for (let i = 0; i < this.columnNumber; i++) {
      this.summaryColumns.push([]);
    }
    for (let i = 0; i < this.summaries.length; i++) {
      const columnIndex = i % this.columnNumber;
      this.summaryColumns[columnIndex].push(this.summaries[i]);
    }
  }

  private prepareSummaries(): void {
    if (this.summaries) {
      return;
    }
    if (!this.category) {
      this.summaries = this.articleSummaryService.getSummariesOrderByCreatedDate();
      return;
    }
    if ('bookmark' === this.category) {
      this.summaries = this.articleSummaryService.getSummariesOrderByBookmarkNumber();
      return;
    }
    if ('comment' === this.category) {
      this.summaries = this.articleSummaryService.getSummariesOrderByCommentNumber();
      return;
    }
    if ('praise' === this.category) {
      this.summaries = this.articleSummaryService.getSummariesOrderByPraiseNumber();
      return;
    }
    if ('view' === this.category) {
      this.summaries = this.articleSummaryService.getSummariesOrderByViewNumber();
      return;
    }
    if ('recent' === this.category) {
      this.summaries = this.articleSummaryService.getSummariesOrderByCreatedDate();
      return;
    }
    if ('labels' === this.category) {
      if (this.targetLabels) {
        this.summaries = this.articleSummaryService.getSummariesOfLabels(this.targetLabels);
        return;
      }
      this.activateRoute.paramMap.subscribe(param => {
        if (param.has('labels')) {
          this.targetLabels = param.get('labels').split(',');
          this.summaries = this.articleSummaryService.getSummariesOfLabels(this.targetLabels);
          return;
        }
        this.summaries = [];
      });
    }
  }
}
