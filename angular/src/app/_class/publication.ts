import { FileAPI } from "./file-api";
import { Comment } from "./comment";

export class Publication {
    public id!: number ;

    public countLike!: number;
    public liked!:boolean;

    public contenu!: string ;
    public username!: string;
 
    public listFile : FileAPI[] = [];
    public listComments : Comment[] = [];

    public createdByUser!: boolean;
}
