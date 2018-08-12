import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ArticleDetailService} from '../../service/article-detail.service';
import {ArticleDetail} from '../../vo/article-detail';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss']
})
export class ArticleDetailComponent implements OnInit {
  private _article: ArticleDetail;

  constructor(private activatedRoute: ActivatedRoute, private articleDetailService: ArticleDetailService) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      const id = parseInt(params.get('id'), 10);
      this._article = this.articleDetailService.get(id);
    });
  }

  get article() {
    return this._article;
  }
}
