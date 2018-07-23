export class ApiRequest<PayloadType> {
  private _header: { [name: string]: string };
  private _payload: PayloadType;

  public constructor() {
    this._header = {};
    this._payload = null;
  }


  get header(): { [p: string]: string } {
    return this._header;
  }

  set header(value: { [p: string]: string }) {
    this._header = value;
  }

  get payload(): PayloadType {
    return this._payload;
  }

  set payload(value: PayloadType) {
    this._payload = value;
  }

  public toJson(): string {
    return JSON.stringify(this);
  }

  public static fromJson<PT>(json: string): ApiRequest<PT> {
    return JSON.parse(json);
  }
}
