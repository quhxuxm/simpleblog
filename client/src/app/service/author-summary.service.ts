import {Injectable} from '@angular/core';
import {AuthorSummary} from '../vo/author-summary';

@Injectable({
  providedIn: 'root'
})
export class AuthorSummaryService {
  constructor() {
  }

  get(id: number): AuthorSummary {
    const result = new AuthorSummary();
    result.id = 1;
    result.description = '测试用户的描述，测试用户的描述，测试用户的描述，测试用户的描述'
      + '测试用户的描述，测试用户的描述，测试用户的描述，测试用户的描述' +
      '测试用户的描述，测试用户的描述，测试用户的描述，测试用户的描述';
    result.followerNumber = 10000;
    result.nickName = '测试用户';
    result.iconImageId = 1;
    result.lastLoginDate = new Date();
    return result;
  }
}
