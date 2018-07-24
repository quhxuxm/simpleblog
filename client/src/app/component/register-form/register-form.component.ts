import {Component, OnInit} from '@angular/core';

import {ApiRequest} from "../../vo/api/ApiRequestModule";
import {RegisterForm} from "../../vo/FormVoModule";
import {HttpHeaders} from "@angular/common/http";
import {ConnectionService} from "../../common/ConnectionService";
import {ApiResponse} from "../../vo/api/ApiResponseModule";

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
    apiRequest.payload = new RegisterForm(this.token, this.password, this.nickName);
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic my-auth-token'
      })
    };
    console.log(apiRequest.toJson());
    let response: ApiResponse<number> = this.connectionService.post<number>("/api/author/register", apiRequest);
    console.log(response.payload)
  }


}
