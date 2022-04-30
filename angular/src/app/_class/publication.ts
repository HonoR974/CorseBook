import { FileAPI } from "./file-api";
import { Comment } from "./comment";
import { Marker } from "./marker";

export class Publication {
    public id!: number ;

    public countLike!: number;
    public liked!:boolean;

    public contenu!: string ;
    public username!: string;
 
    public listFile : FileAPI[] = [];

    listMarker: Marker[] = [];

    public listComments : Comment[] = [];
    public isClickedComment:boolean = false;
    public createdByUser!: boolean;

   //le btn toggle entre map et file 
   checkedMarker:boolean = true;
}


