import { FileAPI } from "./file-api";
import { Comment } from "./comment";

export class Publication {
    public id!: number ;
    public countLike!: number;
    public contenu!: string ;
    public username!: string;
    public liked!:boolean;
    public listFile : FileAPI[] = [];
    public listComments : Comment[] = [];
}
