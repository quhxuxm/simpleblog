export class ArticleDetail {
  private _id: number;
  private _title: string;
  private _createDate: Date;
  private _updateDate: Date;
  private _authorId: number;
  private _authorNickName: string;
  private _authorIconId: number;
  private _anthologyTitle: string;
  private _anthologyId: number;
  private _summary: string;
  private _content: string;
  private _tags: string[];
  private _commentNumber: number;
  private _praiseNumber: number;
  private _bookmarkNumber: number;
  private _viewNumber: number;

  constructor() {
    this.tags = [];
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
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

  get authorIconId(): number {
    return this._authorIconId;
  }

  set authorIconId(value: number) {
    this._authorIconId = value;
  }

  get anthologyTitle(): string {
    return this._anthologyTitle;
  }

  set anthologyTitle(value: string) {
    this._anthologyTitle = value;
  }

  get anthologyId(): number {
    return this._anthologyId;
  }

  set anthologyId(value: number) {
    this._anthologyId = value;
  }

  get summary(): string {
    return this._summary;
  }

  set summary(value: string) {
    this._summary = value;
  }

  get content(): string {
    return this._content;
  }

  set content(value: string) {
    this._content = value;
  }

  get tags(): string[] {
    return this._tags;
  }

  set tags(value: string[]) {
    this._tags = value;
  }

  get commentNumber(): number {
    return this._commentNumber;
  }

  set commentNumber(value: number) {
    this._commentNumber = value;
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

  get viewNumber(): number {
    return this._viewNumber;
  }

  set viewNumber(value: number) {
    this._viewNumber = value;
  }
}
