
export class User {
    id ?: number;
    email ?: string;
    userName ?: string;
    password ?: string;
    appUserRole ?: string;

    constructor(object: any) {
        this.id = object.id;
        this.email = object.email;
        this.userName = object.userName;
        this.password = object.password;
        // this.appUserRole = 'USER';
    }
}