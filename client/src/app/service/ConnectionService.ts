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
   * The sync invocation return a api response object
   *
   * @param {string} url
   * @param {IStringKeyMap<string[]>} queryParams
   * @param {IStringKeyMap<string[]>} headers
   * @returns {ApiResponse<FailPayload | ResponsePayloadType>}
   */
  get<ResponsePayloadType>(url: string,
                           queryParams: IStringKeyMap<string[]>,
                           headers: IStringKeyMap<string[]>): ApiResponse<ResponsePayloadType | FailPayload> {
    let result: ApiResponse<ResponsePayloadType | FailPayload> = null;
    this.httpClient.get<ApiResponse<ResponsePayloadType | FailPayload>>(url, {
      params: queryParams,
      headers: headers
    }).subscribe((response) => {
      result = response;
    }, (error) => {
      console.error("Fail to invoke url: " + url);
    });
    return result;
  }

  /**
   * The async invocation pass the response handler to handle the response.
   *
   * @param {string} url
   * @param {IStringKeyMap<string[]>} queryParams
   * @param {IStringKeyMap<string[]>} headers
   * @param options The options response handler.
   */
  getAsync<ResponsePayloadType>(url: string,
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
   * The sync invocation return a api response object
   *
   * @param {string} url
   * @param {IStringKeyMap<string[]>} queryParams
   * @param {IStringKeyMap<string[]>} headers
   * @param {ApiRequest<RequestBodyPayloadType>} requestBody
   * @returns {ApiResponse<FailPayload | ResponsePayloadType>}
   */
  post<ResponsePayloadType, RequestBodyPayloadType>(url: string,
                                                    queryParams: IStringKeyMap<string[]>,
                                                    headers: IStringKeyMap<string[]>,
                                                    requestBody: ApiRequest<RequestBodyPayloadType>): ApiResponse<ResponsePayloadType | FailPayload> {
    let result: ApiResponse<ResponsePayloadType | FailPayload> = null;
    this.httpClient.post<ApiResponse<ResponsePayloadType | FailPayload>>(url,
      requestBody, {
        params: queryParams,
        headers: headers
      }).subscribe((response) => {
      result = response;
      console.info("The post response body is: "+JSON.stringify(response));
      console.info("The post response body convert to result is: "+JSON.stringify(result));
    }, (error) => {
      console.error("Fail to invoke url: " + url);
    });
    return result;
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
  postAsync<ResponsePayloadType, RequestBodyPayloadType>(url: string,
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
