export class ArticleComment {
  private _authorId: number;
  private _authorNickname: string;
  private _authorIconId: number;
  private _createDate: Date;
  private _updateDate: Date;
  private _content: string;
  private _parent: ArticleComment;

  get authorId(): number {
    return this._authorId;
  }

  set authorId(value: number) {
    this._authorId = value;
  }

  get authorNickname(): string {
    return this._authorNickname;
  }

  set authorNickname(value: string) {
    this._authorNickname = value;
  }

  get authorIconId(): number {
    return this._authorIconId;
  }

  set authorIconId(value: number) {
    this._authorIconId = value;
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

  get content(): string {
    return this._content;
  }

  set content(value: string) {
    this._content = value;
  }

  get parent(): ArticleComment {
    return this._parent;
  }

  set parent(value: ArticleComment) {
    this._parent = value;
  }
}
