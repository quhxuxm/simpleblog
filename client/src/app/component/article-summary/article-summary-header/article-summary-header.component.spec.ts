import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleSummaryHeaderComponent } from './article-summary-header.component';

describe('ArticleSummaryHeaderComponent', () => {
  let component: ArticleSummaryHeaderComponent;
  let fixture: ComponentFixture<ArticleSummaryHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleSummaryHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleSummaryHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
