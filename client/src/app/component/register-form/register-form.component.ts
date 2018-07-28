import {Component, Inject, OnInit} from '@angular/core';
import {IAuthenticationService} from "../../service/api/IAuthenticationService";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  public token: string;
  public password: string;
  public nickName: string;

  constructor(@Inject("authenticationService") private authenticationService: IAuthenticationService) {
  }

  ngOnInit() {
  }

  public onSubmit(): void {
    this.authenticationService.register(this.token, this.password, this.nickName, (id) => {
      console.log("Register the author with id = " + id);
    }, (failType) => {
      console.log("Fail to register author because of: " + failType)
    });
  }
}
