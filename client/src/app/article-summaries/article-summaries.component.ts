import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Component, OnInit} from '@angular/core';
import {ArticleSummary} from '../vo/article-summary';

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
  columnNumber: number;
  summaries: ArticleSummary[] = [];
  summaryColumns: ArticleSummary[][] = [];

  constructor(private breakpointObserver: BreakpointObserver) {
    this.columnNumber = 1;
  }

  ngOnInit() {
    for (let i = 0; i < 53; i++) {
      const s =
        new ArticleSummary(i,
          `测试文章的标题ABC ${i}`,
          `测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，`,
          new Date(),
          new Date(),
          i + 100,
          `测试作者ABC-昵称 ${i}`, i + 200,
          i + 300, `测试文集的标题ABC ${i}`,
          i + 400, 10000, 10000,
          10000, 10000, i + 500);
      s.tags.push(... ['TAG1', 'TAG2', 'TAG3', 'TAG4', 'TAG5']);
      this.summaries.push(s);
    }
    this.BREAKPOINT_COLUMN_NUMBER.forEach((v, k, m) => {
      this.breakpointObserver.observe(k)
        .subscribe(
          () => {
            if (this.breakpointObserver.isMatched(k)) {
              this.columnNumber = v;
              this.fillArticleSummaries();
              console.log('Set column number to: ' + this.columnNumber);
            }
          }
        );
    });
  }

  private fillArticleSummaries() {
    this.summaryColumns = [];
    for (let i = 0; i < this.columnNumber; i++) {
      this.summaryColumns.push([]);
    }
    for (let i = 0; i < this.summaries.length; i++) {
      const columnIndex = i % this.columnNumber;
      this.summaryColumns[columnIndex].push(this.summaries[i]);
    }
  }
}
