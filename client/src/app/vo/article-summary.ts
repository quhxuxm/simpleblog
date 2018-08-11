export class ArticleSummary {
  private _articleId: number;
  private _title: string;
  private _summary: string;
  private _createDate: Date;
  private _updateDate: Date;
  private _authorId: number;
  private _authorNickName: string;
  private _authorIconImageId: number;
  private _anthologyId: number;
  private _anthologyTitle: string;
  private _anthologyCoverImageId: number;
  private _praiseNumber: number;
  private _bookmarkNumber: number;
  private _commentNumber: number;
  private _viewNumber: number;
  private _coverImageId: number;
  private _tags: string[];

  constructor(articleId: number,
              title: string,
              summary: string,
              createDate: Date,
              updateDate: Date,
              authorId: number,
              authorNickName: string,
              authorIconImageId: number,
              anthologyId: number,
              anthologyTitle: string,
              anthologyCoverImageId: number,
              praiseNumber: number,
              bookmarkNumber: number,
              commentNumber: number,
              viewNumber: number,
              coverImageId: number) {
    this._articleId = articleId;
    this._title = title;
    this._summary = summary;
    this._createDate = createDate;
    this._updateDate = updateDate;
    this._authorId = authorId;
    this._authorNickName = authorNickName;
    this._authorIconImageId = authorIconImageId;
    this._anthologyId = anthologyId;
    this._anthologyTitle = anthologyTitle;
    this._anthologyCoverImageId = anthologyCoverImageId;
    this._praiseNumber = praiseNumber;
    this._bookmarkNumber = bookmarkNumber;
    this._commentNumber = commentNumber;
    this._viewNumber = viewNumber;
    this._coverImageId = coverImageId;
    this._tags = [];
  }

  get articleId(): number {
    return this._articleId;
  }

  set articleId(value: number) {
    this._articleId = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get summary(): string {
    return this._summary;
  }

  set summary(value: string) {
    this._summary = value;
  }

  get createDate(): Date {
    return this._createDate;
  }

  set createDate(value: Date) {
    this._createDate = value;
  }

  get updateDate(): Date {
    return this._updateDate;
  }

  set updateDate(value: Date) {
    this._updateDate = value;
  }

  get authorId(): number {
    return this._authorId;
  }

  set authorId(value: number) {
    this._authorId = value;
  }

  get authorNickName(): string {
    return this._authorNickName;
  }

  set authorNickName(value: string) {
    this._authorNickName = value;
  }

  get authorIconImageId(): number {
    return this._authorIconImageId;
  }

  set authorIconImageId(value: number) {
    this._authorIconImageId = value;
  }

  get anthologyId(): number {
    return this._anthologyId;
  }

  set anthologyId(value: number) {
    this._anthologyId = value;
  }

  get anthologyTitle(): string {
    return this._anthologyTitle;
  }

  set anthologyTitle(value: string) {
    this._anthologyTitle = value;
  }

  get anthologyCoverImageId(): number {
    return this._anthologyCoverImageId;
  }

  set anthologyCoverImageId(value: number) {
    this._anthologyCoverImageId = value;
  }

  get praiseNumber(): number {
    return this._praiseNumber;
  }

  set praiseNumber(value: number) {
    this._praiseNumber = value;
  }

  get bookmarkNumber(): number {
    return this._bookmarkNumber;
  }

  set bookmarkNumber(value: number) {
    this._bookmarkNumber = value;
  }

  get commentNumber(): number {
    return this._commentNumber;
  }

  set commentNumber(value: number) {
    this._commentNumber = value;
  }

  get viewNumber(): number {
    return this._viewNumber;
  }

  set viewNumber(value: number) {
    this._viewNumber = value;
  }

  get coverImageId(): number {
    return this._coverImageId;
  }

  set coverImageId(value: number) {
    this._coverImageId = value;
  }

  get tags(): string[] {
    return this._tags;
  }
}
