export class PasswordUpdate {
  constructor(
    public token: string,
    public oldPassword: string,
    public password: string,
    public matchPassword: string
  ) {}
}
