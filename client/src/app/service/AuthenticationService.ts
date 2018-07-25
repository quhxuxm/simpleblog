import {Inject, Injectable} from "@angular/core";
import {FailPayloadType} from "../vo/api/ApiResponseModule";
import {LoginForm, RegisterForm} from "../vo/FormVoModule";
import {ApiRequest} from "../vo/api/ApiRequestModule";
import {IConnectionService} from "./api/IConnectionService";
import {IAuthenticationService} from "./api/IAuthenticationService";
import {IStringKeyMap} from "../util";

@Injectable()
export class AuthenticationService implements IAuthenticationService {
  constructor(@Inject("connectionService") private connectionService: IConnectionService) {
  }

  register(token: string, password: string, nickName: string,
           registerSuccessHandler: (id: number) => void,
           registerFailHandler: (errorType: FailPayloadType) => void): void {
    let apiRequest: ApiRequest<RegisterForm> = new ApiRequest();
    apiRequest.payload =
      new RegisterForm(token, password, nickName);
    this.connectionService.post<number, RegisterForm>(
      "/api/author/register", null, null, apiRequest, {
        handleResponse: (response) => registerSuccessHandler(
          response.payload),
        handleFailResponse: (failResponse) => registerFailHandler(
          failResponse.payload.type)
      });
  }

  login(token: string, password: string, loginSuccessHandler: () => void,
        loginFailHandler: (errorType: FailPayloadType) => void): void {
    let queryParams: IStringKeyMap<string[]> = {
      "username": [token],
      "password": [password]
    }
    this.connectionService.post<number, LoginForm>(
      "/api/author/authenticate", queryParams, null, null, {
        handleResponse: (response) => loginSuccessHandler(),
        handleFailResponse: (failResponse) => loginFailHandler(
          failResponse.payload.type)
      });
  }
}
