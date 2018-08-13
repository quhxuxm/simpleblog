import {ArticleSummary} from './article-summary';

export class AnthologyDetail {
  private _id: number;
  private _title: string;
  private _createDate: Date;
  private _updateDate: Date;
  private _authorId: number;
  private _authorNickName: string;
  private _authorIconId: number;
  private _summary: string;
  private _tags: string[];
  private _commentNumber: number;
  private _praiseNumber: number;
  private _bookmarkNumber: number;
  private _articleSummaries: ArticleSummary[];

  constructor() {
    this._articleSummaries = [];
    this._tags = [];
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

  get summary(): string {
    return this._summary;
  }

  set summary(value: string) {
    this._summary = value;
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

  get articleSummaries(): ArticleSummary[] {
    return this._articleSummaries;
  }

  set articleSummaries(value: ArticleSummary[]) {
    this._articleSummaries = value;
  }
}
