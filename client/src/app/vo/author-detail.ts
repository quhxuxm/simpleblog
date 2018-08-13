export class AuthorDetail {
  private _id: number;
  private _nickName: string;
  private _token: string;
  private _lastLoginDate: Date;
  private _registerDate: Date;
  private _roles: string[];
  private _authorIconImageId: number;
  private _articleNumber: number;
  private _commentNumber: number;
  private _anthologyNumber: number;
  private _followedByNumber: number;
  private _tags: string[];
  private _defaultAnthologyId: number;

  constructor() {
    this._tags = [];
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get nickName(): string {
    return this._nickName;
  }

  set nickName(value: string) {
    this._nickName = value;
  }

  get token(): string {
    return this._token;
  }

  set token(value: string) {
    this._token = value;
  }

  get lastLoginDate(): Date {
    return this._lastLoginDate;
  }

  set lastLoginDate(value: Date) {
    this._lastLoginDate = value;
  }

  get registerDate(): Date {
    return this._registerDate;
  }

  set registerDate(value: Date) {
    this._registerDate = value;
  }

  get roles(): string[] {
    return this._roles;
  }

  set roles(value: string[]) {
    this._roles = value;
  }

  get authorIconImageId(): number {
    return this._authorIconImageId;
  }

  set authorIconImageId(value: number) {
    this._authorIconImageId = value;
  }

  get articleNumber(): number {
    return this._articleNumber;
  }

  set articleNumber(value: number) {
    this._articleNumber = value;
  }

  get commentNumber(): number {
    return this._commentNumber;
  }

  set commentNumber(value: number) {
    this._commentNumber = value;
  }

  get anthologyNumber(): number {
    return this._anthologyNumber;
  }

  set anthologyNumber(value: number) {
    this._anthologyNumber = value;
  }

  get followedByNumber(): number {
    return this._followedByNumber;
  }

  set followedByNumber(value: number) {
    this._followedByNumber = value;
  }

  get tags(): string[] {
    return this._tags;
  }

  set tags(value: string[]) {
    this._tags = value;
  }

  get defaultAnthologyId(): number {
    return this._defaultAnthologyId;
  }

  set defaultAnthologyId(value: number) {
    this._defaultAnthologyId = value;
  }
}
