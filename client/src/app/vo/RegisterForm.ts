export class RegisterForm {

  constructor(public userName: string, public password: string, public nickName: string) {
  }

  public toJson(): string {
    return JSON.stringify(this);
  }
}
