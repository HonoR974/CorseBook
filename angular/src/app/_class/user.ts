import { Evenement } from "./evenement";

export class User {
    id!: number ;
    username!: string;
    email?: string ;
    listContact: User[] = [];
    listInvitation: User[] = [];
    id_chat?:number;
    urlFile?:string;
    invitedOrContact!:boolean;

}
