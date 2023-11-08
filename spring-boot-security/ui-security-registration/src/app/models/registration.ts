export class Registration {
  constructor(
    public firstname: string,
    public lastname: string,
    public email: string,
    public password: string,
    public matchPassword: string,
    public twoStepVer: string
  ) {}
}
