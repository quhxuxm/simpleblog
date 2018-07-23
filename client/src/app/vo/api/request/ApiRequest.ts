export class ApiRequest<PayloadType> {
  public header: { [name: string]: string };
  public payload: PayloadType;

  public constructor() {
    this.header = {};
    this.payload = null;
  }

  public toJson(): string {
    return JSON.stringify(this);
  }
}
