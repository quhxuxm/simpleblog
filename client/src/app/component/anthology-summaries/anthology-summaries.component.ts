import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material';

@Component({
  selector: 'app-anthology-summaries',
  templateUrl: './anthology-summaries.component.html',
  styleUrls: ['./anthology-summaries.component.scss']
})
export class AnthologySummariesComponent implements OnInit {
  @ViewChild(MatPaginator)
  articleSummariesListPaginator: MatPaginator;

  constructor() {
  }

  ngOnInit() {
    this.articleSummariesListPaginator._intl.previousPageLabel = '上一页';
    this.articleSummariesListPaginator._intl.nextPageLabel = '下一页';
  }
}
