import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleSummaryBodyComponent } from './article-summary-body.component';

describe('ArticleSummaryBodyComponent', () => {
  let component: ArticleSummaryBodyComponent;
  let fixture: ComponentFixture<ArticleSummaryBodyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleSummaryBodyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleSummaryBodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
