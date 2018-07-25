import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {IStringKeyMap} from "../util";
import {ApiResponse, FailPayload} from "../vo/api/ApiResponseModule";
import {ApiRequest} from "../vo/api/ApiRequestModule";

@Injectable()
export class ConnectionService {
  constructor(private httpClient: HttpClient) {
  }

  /**
   * The async invocation pass the response handler to handle the response.
   *
   * @param {string} url
   * @param {IStringKeyMap<string[]>} queryParams
   * @param {IStringKeyMap<string[]>} headers
   * @param options The options response handler.
   */
  get<ResponsePayloadType>(url: string,
                           queryParams: IStringKeyMap<string[]>,
                           headers: IStringKeyMap<string[]>, options?: {
      handleResponse?: {
        (response: ApiResponse<ResponsePayloadType>): void;
      }; handleFailResponse?: {
        (error: ApiResponse<FailPayload>): void;
      };
    }
  ): void {
    this.httpClient.get<ApiResponse<ResponsePayloadType | FailPayload>>(url, {
      params: queryParams,
      headers: headers
    }).subscribe((response) => {
      if (response.payload instanceof FailPayload) {
        options.handleFailResponse(<ApiResponse<FailPayload>>response);
        return;
      }
      options.handleResponse(<ApiResponse<ResponsePayloadType>>response);
    }, (error) => {
      console.error("Fail to invoke url: " + url);
    });
  }

  /**
   * The async invocation pass the response handler to handle the response.
   *
   * @param {string} url
   * @param {IStringKeyMap<string[]>} queryParams
   * @param {IStringKeyMap<string[]>} headers
   * @param {ApiRequest<RequestBodyPayloadType>} requestBody
   * @param options The options response handler.
   */
  post<ResponsePayloadType, RequestBodyPayloadType>(url: string,
                                                    queryParams: IStringKeyMap<string[]>,
                                                    headers: IStringKeyMap<string[]>,
                                                    requestBody: ApiRequest<RequestBodyPayloadType>,
                                                    options?: {
                                                      handleResponse?: {
                                                        (response: ApiResponse<ResponsePayloadType>): void;
                                                      };
                                                      handleFailResponse?: {
                                                        (error: ApiResponse<FailPayload>): void;
                                                      };
                                                    }
  ): void {
    this.httpClient.post<ApiResponse<ResponsePayloadType | FailPayload>>(url,
      requestBody, {
        params: queryParams,
        headers: headers
      }).subscribe((response) => {
      if (response.payload instanceof FailPayload) {
        options.handleFailResponse(<ApiResponse<FailPayload>>response);
        return;
      }
      options.handleResponse(<ApiResponse<ResponsePayloadType>>response);
    }, (error) => {
      console.error("Fail to invoke url: " + url);
    });
  }
}
