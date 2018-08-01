export class LoginForm {
  constructor(public token: string, public password: string) {
  }
}

export class RegisterForm {

  constructor(public token: string, public password: string, public nickName: string) {
  }

  public toJson(): string {
    return JSON.stringify(this);
  }
}

