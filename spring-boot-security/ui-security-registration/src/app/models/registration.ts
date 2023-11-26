export class Registration {
  constructor(
    public username: string,
    public firstname: string,
    public lastname: string,
    public email: string,
    public birthdate: string,
    public password: string,
    public matchPassword: string,
    public isUsing2FA: boolean
  ) {}
}
