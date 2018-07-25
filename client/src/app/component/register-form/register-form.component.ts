import {Component, OnInit} from '@angular/core';
import {ApiRequest} from "../../vo/api/ApiRequestModule";
import {RegisterForm} from "../../vo/FormVoModule";
import {HttpHeaders} from "@angular/common/http";
import {ConnectionService} from "../../service/ConnectionService";
import {ApiResponse, FailPayload} from "../../vo/api/ApiResponseModule";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  public token: string;
  public password: string;
  public nickName: string;

  constructor(private connectionService: ConnectionService) {
  }

  ngOnInit() {
  }

  public onSubmit(): void {
    let apiRequest: ApiRequest<RegisterForm> = new ApiRequest();
    apiRequest.payload =
      new RegisterForm(this.token, this.password, this.nickName);
    console.log(apiRequest.toJson());
    let response: ApiResponse<number | FailPayload> = this.connectionService.post<number, RegisterForm>(
      "/api/author/register", null, null, apiRequest);
    console.log(response.payload)
  }
}
