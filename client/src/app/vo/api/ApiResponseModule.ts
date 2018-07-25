export enum ApiResponseStatus {
  SUCCESS,
  FAIL
}

export class FailPayload {
  constructor(public message: string, public type: FailPayloadType) {
  }
}

export enum FailPayloadType {
  AUTHENTICATE_REQUIRED
}

export class ApiResponse<PayloadType> {
  public header: Map<string, string>;
  public payload: PayloadType;
  public status: ApiResponseStatus;

  public constructor() {
    this.header = new Map();
    this.payload = null;
    this.status = ApiResponseStatus.SUCCESS;
  }

  public static fromJson<PT>(json: string): ApiResponse<PT> {
    return JSON.parse(json);
  }
}
