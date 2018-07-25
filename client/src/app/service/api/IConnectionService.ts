import {IStringKeyMap} from "../../util";
import {ApiResponse, FailPayload} from "../../vo/api/ApiResponseModule";
import {ApiRequest} from "../../vo/api/ApiRequestModule";

export interface IConnectionService {
  get<ResponsePayloadType>(url: string,
                           queryParams: IStringKeyMap<string[]>,
                           headers: IStringKeyMap<string[]>, options?: {
      handleResponse?: {
        (response: ApiResponse<ResponsePayloadType>): void;
      }; handleFailResponse?: {
        (error: ApiResponse<FailPayload>): void;
      };
    }
  ): void;

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
  ): void;
}
