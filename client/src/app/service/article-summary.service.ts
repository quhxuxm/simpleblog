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
    for (let i = 1; i <= 12; i++) {
      const s =
        new ArticleSummary();
      s.id = i;
      s.title = `测试文章的标题ABC ${i}`;
      s.summary = `测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，`;
      s.createDate = new Date();
      s.updateDate = new Date();
      s.authorId = i;
      s.authorNickName = `测试作者ABC-昵称 ${i}`;
      s.anthologyTitle = `测试文集的标题ABC ${i}`;
      s.anthologyId = i;
      s.bookmarkNumber = 10000;
      s.commentNumber = 10000;
      s.praiseNumber = 10000;
      s.viewNumber = 10000;
      s.authorIconImageId = i;
      s.anthologyCoverImageId = i;
      s.coverImageId = i;
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
