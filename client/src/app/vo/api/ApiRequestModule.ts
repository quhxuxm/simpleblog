export class ApiRequest<PayloadType> {
  public header: Map<string,string>;
  public payload: PayloadType;

  public constructor() {
    this.header = new Map();
    this.payload = null;
  }

  public toJson(): string {
    return JSON.stringify(this);
  }
}
