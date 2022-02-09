export class User {
    id?: number ;
    username?: string ;
    email?: string ;
    listContact: User[] = [];
    listInvitation: User[] = [];
}
