import {Component, Input, OnInit} from '@angular/core';
import {AuthorSummaryService} from '../../service/author-summary.service';
import {AuthorSummary} from '../../vo/author-summary';

@Component({
  selector: 'app-author-info-card',
  templateUrl: './author-info-card.component.html',
  styleUrls: ['./author-info-card.component.scss']
})
export class AuthorInfoCardComponent implements OnInit {
  @Input()
  author: AuthorSummary;
  @Input()
  authorId: number;

  constructor(private authorSummaryService: AuthorSummaryService) {
    this.author = null;
    this.authorId = null;
  }

  ngOnInit() {
    if (this.author != null) {
      return;
    }
    if (this.authorId != null) {
      this.author = this.authorSummaryService.get(this.authorId);
    }
  }
}
