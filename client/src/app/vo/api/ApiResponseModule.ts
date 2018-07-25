export enum ApiResponseStatus {
  SUCCESS = "SUCCESS",
  FAIL = "FAIL"
}

export class FailPayload {
  constructor(public message: string, public type: FailPayloadType) {
  }
}

export enum FailPayloadType {
  AUTHENTICATION_ERROR__AUTHENTICATION_REQUIRED = "AUTHENTICATION_ERROR__AUTHENTICATION_REQUIRED",
  AUTHENTICATION_ERROR__TOKEN_NOT_EXIST = "AUTHENTICATION_ERROR__TOKEN_NOT_EXIST",
  AUTHOR_ERROR__TOKEN_EXIST = "AUTHOR_ERROR__TOKEN_EXIST",
  AUTHOR_ERROR__NICK_NAME_EXIST = "AUTHOR_ERROR__NICK_NAME_EXIST",
  UNKNOWN_ERROR__SERVICE = "UNKNOWN_ERROR__SERVICE",
  UNKNOWN_ERROR__SYSTEM = "UNKNOWN_ERROR__SYSTEM",
  ARTICLE_ERROR__NOT_EXIST = "ARTICLE_ERROR__NOT_EXIST",
  AUTHOR_ERROR__NOT_EXIST = "AUTHOR_ERROR__NOT_EXIST"
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
}
