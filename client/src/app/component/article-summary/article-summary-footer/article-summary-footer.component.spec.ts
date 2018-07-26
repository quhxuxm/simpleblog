import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleSummaryFooterComponent } from './article-summary-footer.component';

describe('ArticleSummaryFooterComponent', () => {
  let component: ArticleSummaryFooterComponent;
  let fixture: ComponentFixture<ArticleSummaryFooterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleSummaryFooterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleSummaryFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
