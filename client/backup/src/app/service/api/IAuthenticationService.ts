import {FailPayloadType} from '../../vo/api/ApiResponseModule';

export interface IAuthenticationService {
  register(token: string, password: string, nickName: string,
           registerSuccessHandler: (id: number) => void,
           registerFailHandler: (errorType: FailPayloadType) => void): void;

  login(token: string, password: string, loginSuccessHandler: () => void,
        loginFailHandler: (errorType: FailPayloadType) => void): void;
}
