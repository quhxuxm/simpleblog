import { Component, Inject, OnInit } from '@angular/core';
import { IAuthenticationService } from '../service/api/IAuthenticationService';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  public token: string;
  public password: string;

  constructor(@Inject('authenticationService') private authenticationService: IAuthenticationService) {
  }

  ngOnInit() {
  }

  public onSubmit(): void {
    this.authenticationService.login(this.token, this.password, () => {
      console.log('Login success');
    }, (faileType) => {
      console.log('Login fail because of: ' + faileType);
    });
  }
}
