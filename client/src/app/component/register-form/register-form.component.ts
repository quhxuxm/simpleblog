import {Component, OnInit} from '@angular/core';

import {ApiRequest} from "../../vo/api/ApiRequestModule";
import {RegisterForm} from "../../vo/FormVoModule";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  public token: string;
  public password: string;
  public nickName: string;

  constructor(private httpClient: HttpClient) {
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
    this.httpClient.post<ApiRequest<RegisterForm>>("/api/author/register", apiRequest, httpOptions).subscribe(response => console.log(response));;
  }


}
