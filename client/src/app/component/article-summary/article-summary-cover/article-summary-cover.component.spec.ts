import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleSummaryCoverComponent } from './article-summary-cover.component';

describe('ArticleSummaryCoverComponent', () => {
  let component: ArticleSummaryCoverComponent;
  let fixture: ComponentFixture<ArticleSummaryCoverComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleSummaryCoverComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleSummaryCoverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
