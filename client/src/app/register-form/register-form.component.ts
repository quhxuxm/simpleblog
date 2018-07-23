import {Component, OnInit} from '@angular/core';
import {RegisterForm} from "../vo/RegisterForm";
import {ApiRequest} from "../vo/api/request/ApiRequest";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  public userName: string;
  public password: string;
  public nickName: string;

  constructor() {
  }

  ngOnInit() {
  }

  public onSubmit(): void {
    let apiRequest: ApiRequest<RegisterForm> = new ApiRequest();
    apiRequest.payload = new RegisterForm(this.userName, this.password, this.nickName);
    console.log(apiRequest.toJson());
  }
}
