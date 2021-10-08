export class AdminEntity {

    public id: number | undefined;
    public username: string | undefined;
    public password: string | undefined;
    public role: string | undefined;

    constructor( id?: number | undefined, username?: string | undefined, password?: string | undefined, role?: string | undefined ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
