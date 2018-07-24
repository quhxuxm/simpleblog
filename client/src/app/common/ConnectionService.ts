import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {ApiResponse, ApiResponseStatus} from "../vo/api/ApiResponseModule";
import {ApiRequest} from "../vo/api/ApiRequestModule";
import {RegisterForm} from "../vo/FormVoModule";

@Injectable()
export class ConnectionService {
  constructor(private  httpClient: HttpClient) {
  }

  public get<ResponsePayloadType>(url: string): ApiResponse<ResponsePayloadType> {
    let result = null;
    this.httpClient.get<ApiResponse<ResponsePayloadType>>(url).subscribe((data: ApiResponse<ResponsePayloadType>) => result = data);
    return result;
  }

  public post<ResponsePayloadType>(url: string, requestBody: ApiRequest<RegisterForm>): ApiResponse<ResponsePayloadType> {
    let result = null;
    this.httpClient.post<ApiResponse<ResponsePayloadType>>(url, requestBody).subscribe((data: ApiResponse<ResponsePayloadType>) => result = data);
    if(result == null){

    }
    if(result.status == ApiResponseStatus.FAIL){

    }
    return result;
  }
}
