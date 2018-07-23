import {Component, OnInit} from '@angular/core';

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

  }


}
