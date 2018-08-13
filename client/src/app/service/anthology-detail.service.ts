import {Injectable} from '@angular/core';
import {AnthologyDetail} from '../vo/anthology-detail';
import {ArticleSummary} from '../vo/article-summary';
import {ArticleSummaryService} from './article-summary.service';

@Injectable({
  providedIn: 'root'
})
export class AnthologyDetailService {
  constructor(private articleSummaryService: ArticleSummaryService) {
  }

  get(id: number): AnthologyDetail {
    const result = new AnthologyDetail();
    result.title = '测试文集标题ABC';
    result.authorNickName = '测试作者昵称ABC';
    let tempContent = '';
    for (let i = 0; i < 50; i++) {
      tempContent += '测试内容ABC，DEF一些测试';
    }
    result.summary = tempContent;
    result.tags.push(...['标签TAG1', '标签TAG2', 'TAG3', '标签TAG4', 'TAG5']);
    result.praiseNumber = 10000;
    result.commentNumber = 10000;
    result.bookmarkNumber = 10000;
    result.updateDate = new Date();
    result.id = 1;
    result.articleSummaries = this.generateArticleSummaries(id);
    return result;
  }

  private generateArticleSummaries(anthologyId: number): ArticleSummary[] {
    return this.articleSummaryService.getSummariesOfAnthology(anthologyId);
  }
}
