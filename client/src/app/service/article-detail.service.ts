import {Injectable} from '@angular/core';
import {ArticleDetail} from '../vo/article-detail';

@Injectable({
  providedIn: 'root'
})
export class ArticleDetailService {
  constructor() {
  }

  get(id: number): ArticleDetail {
    const result = new ArticleDetail();
    result.title = '测试文章标题ABC';
    result.authorNickName = '测试作者昵称ABC';
    let tempContent = '';
    for (let i = 0; i < 100; i++) {
      tempContent += '测试内容ABC，DEF一些测试';
    }
    result.content = tempContent;
    result.tags.push(...['标签TAG1', '标签TAG2', 'TAG3', '标签TAG4', 'TAG5']);
    result.praiseNumber = 10000;
    result.commentNumber = 10000;
    result.bookmarkNumber = 10000;
    result.viewNumber = 10000;
    result.updateDate = new Date();
    result.anthologyTitle = '测试文集标题ABC';
    result.anthologyId=1;
    return result;
  }
}
