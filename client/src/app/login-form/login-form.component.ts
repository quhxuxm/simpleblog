import {Component, OnInit} from '@angular/core';
import {ApiRequest} from "../vo/api/request/ApiRequest";
import {LoginForm} from "../vo/LoginForm";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  public userName: string;
  public password: string;

  constructor() {
  }

  ngOnInit() {
  }

  public onSubmit(): void {
    let apiRequest: ApiRequest<LoginForm> = new ApiRequest<LoginForm>();
    apiRequest.payload = new LoginForm(this.userName, this.password);
    console.log(apiRequest.toJson());
  }
}
