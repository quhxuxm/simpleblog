import {Component, OnInit} from '@angular/core';
import {AnthologyDetailService} from '../../service/anthology-detail.service';
import {AnthologyDetail} from '../../vo/anthology-detail';

@Component({
  selector: 'app-anthology-detail',
  templateUrl: './anthology-detail.component.html',
  styleUrls: ['./anthology-detail.component.scss']
})
export class AnthologyDetailComponent implements OnInit {
  anthology: AnthologyDetail;
  displayColumns = ['title', 'createDate', 'updateDate'];

  constructor(private anthologyDetailService: AnthologyDetailService) {
  }

  ngOnInit() {
    this.anthology = this.anthologyDetailService.get(0);
  }
}
