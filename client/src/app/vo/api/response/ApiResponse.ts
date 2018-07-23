import {ApiResponseStatus} from "./ApiResponseStatus";
import {ApiRequest} from "../request/ApiRequest";

export class ApiResponse<PayloadType> {

  private _header: { [name: string]: string };
  private _payload: PayloadType;
  private _status: ApiResponseStatus;

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


  get status(): ApiResponseStatus {
    return this._status;
  }

  set status(value: ApiResponseStatus) {
    this._status = value;
  }

  public toJson(): string {
    return JSON.stringify(this);
  }

  public static fromJson<PT>(json: string): ApiResponse<PT> {
    return JSON.parse(json);
  }
}
