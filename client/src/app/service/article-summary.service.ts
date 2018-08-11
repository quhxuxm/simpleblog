import {Injectable} from '@angular/core';
import {ArticleSummary} from '../vo/article-summary';

@Injectable({
  providedIn: 'root'
})
export class ArticleSummaryService {
  constructor() {
  }

  generateMockSummaries(): ArticleSummary[] {
    const summaries: ArticleSummary[] = [];
    for (let i = 0; i < 6; i++) {
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
      summaries.push(s);
    }
    return summaries;
  }

  getSummariesOrderByBookmarkNumber(): ArticleSummary[] {
    console.log('getSummariesOrderByBookmarkNumber');
    return this.generateMockSummaries();
  }

  getSummariesOrderByCreatedDate(): ArticleSummary[] {
    console.log('getSummariesOrderByCreatedDate');
    return this.generateMockSummaries();
  }

  getSummariesOrderByViewNumber(): ArticleSummary[] {
    console.log('getSummariesOrderByViewNumber');
    return this.generateMockSummaries();
  }

  getSummariesOrderByPraiseNumber(): ArticleSummary[] {
    console.log('getSummariesOrderByPraiseNumber');
    return this.generateMockSummaries();
  }

  getSummariesOrderByCommentNumber(): ArticleSummary[] {
    console.log('getSummariesOrderByCommentNumber');
    return this.generateMockSummaries();
  }

  getSummariesOfLabels(labels: string[]): ArticleSummary[] {
    console.log('getSummariesOfLabels with labels = ' + labels.join(','));
    return this.generateMockSummaries();
  }
}
