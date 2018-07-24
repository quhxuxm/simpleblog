import {StringKeyMap} from "../../common/Util";

export class ApiRequest<PayloadType> {
  public header: StringKeyMap<string>;
  public payload: PayloadType;

  public constructor() {
    this.header = {};
    this.payload = null;
  }

  public toJson(): string {
    return JSON.stringify(this);
  }
}
