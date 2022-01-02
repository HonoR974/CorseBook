import { FileAPI } from "./file-api";

export class Publication {
    public id!: number ;
    public countLike!: number;
    public contenu!: string ;
    public username!: string;
    public listFile : FileAPI[] = [];
}
